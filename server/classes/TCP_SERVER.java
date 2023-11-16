import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class TCP_SERVER {
    private ServerSocket serverSocket;
    private Socket[] clientSockets;
    private int connectedClients;

    /**
     * Costruttore
     * 
     * @throws IOException
     */
    public TCP_SERVER() throws IOException {
        this.serverSocket = new ServerSocket(666);
        this.clientSockets = new Socket[2];
        this.connectedClients = 0;
    }

    /**
     * Attesa della connessione dei due client e successivo invio messaggio di avvio
     * del gioco
     * 
     * @throws IOException
     */
    public String[] startGame() throws IOException {
        String[] tmp = new String[2];
        System.out.println("Server in attesa di connessioni...");

        while (this.connectedClients < 2) {
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("Client connesso: " + clientSocket.getInetAddress());

            clientSockets[this.connectedClients] = clientSocket;

            tmp[this.connectedClients] = clientSocket.getInetAddress() + "";
            this.connectedClients++;
        }

        System.out.println("Entrambi i client sono connessi. Invio il messaggio di avvio.");

        for (Socket clientSocket : this.clientSockets) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("AVVIO");
        }

        // chiude i socket dei client
        for (Socket clientSocket : this.clientSockets) {
            clientSocket.close();
        }

        // chiude il socket del server
        this.serverSocket.close();

        return tmp;
    }

    /**
     * Invia il campo iniziale ai due client
     * 
     * @param xml stringa xml del campo
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void sendGeneratedField(String xml, XML xmlService)
            throws IOException, TransformerException, ParserConfigurationException {
        System.out.println("Server in attesa di connessioni...");

        while (this.connectedClients < 2) {
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("Client connesso: " + clientSocket.getInetAddress());

            clientSockets[this.connectedClients] = clientSocket;
            this.connectedClients++;
        }

        System.out.println("Entrambi i client sono connessi. Invio il campo iniziale.");

        for (Socket clientSocket : this.clientSockets) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(xml);

            // l'altro client deve avere la pallina invertita
            Field tmp = xmlService.fromXML(xml);
            if (tmp.getBall().getDirectionX() == 'l') {
                tmp.getBall().setDirectionX('r');
            } else {
                tmp.getBall().setDirectionX('l');
            }
            xml = xmlService.fieldToXML(tmp);
        }

        // chiude i socket dei client
        for (Socket clientSocket : this.clientSockets) {
            clientSocket.close();
        }

        // chiude il socket del server
        this.serverSocket.close();
    }

    public void updateFields(String[] sortedPlayers, XML xmlService) throws IOException, TransformerException, ParserConfigurationException {
        String[] fieldsSent = new String[2];
        System.out.println("Server in attesa di connessioni...");

        while (this.connectedClients < 2) {
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("Client connesso: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            fieldsSent[this.connectedClients] = in.readLine();

            clientSockets[this.connectedClients] = clientSocket;
            this.connectedClients++;
        }

        System.out.println("Entrambi i client sono connessi. Inizio procedura.");

        // riordina campi in base a ordine iniziale di player
        if (!((clientSockets[0].getInetAddress() + "").contains(sortedPlayers[0]))) { // disordinati
            String tmp = fieldsSent[0];
            fieldsSent[0] = fieldsSent[1];
            fieldsSent[1] = tmp;

            Socket tmpSocket = this.clientSockets[0];
            this.clientSockets[0] = this.clientSockets[1];
            this.clientSockets[1] = tmpSocket;
        }

        // conversione campi
        Field[] tmpFieldList = new Field[2];
        for (int i = 0; i < 2; i++) {
            tmpFieldList[i] = xmlService.fromXML(fieldsSent[i]);
        }

        // inizio modifica campi
        FieldUpdater fieldUpdater = new FieldUpdater(tmpFieldList[0], tmpFieldList[1]);
        // swap informazioni
        fieldUpdater.swapInfo();
        // controlli vari
        fieldUpdater.controls();

        tmpFieldList[0] = fieldUpdater.getFieldOne();
        tmpFieldList[1] = fieldUpdater.getFieldTwo();

        int i = 0;
        for (Socket clientSocket : this.clientSockets) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(xmlService.fieldToXML(tmpFieldList[i]));
        }

        // chiude i socket dei client
        for (Socket clientSocket : this.clientSockets) {
            clientSocket.close();
        }

        // chiude il socket del server
        this.serverSocket.close();
    }

}
