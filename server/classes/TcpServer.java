import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class TcpServer {
    private ServerSocket serverSocket;
    private Socket[] clientSockets;
    private int connectedClients;

    /**
     * Costruttore
     * 
     * @throws IOException
     */
    public TcpServer() throws IOException {
        this.serverSocket = new ServerSocket(667);
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
        // System.out.println("Server in attesa di connessioni...");

        while (this.connectedClients < 2) {
            Socket clientSocket = this.serverSocket.accept();
            // System.out.println("Client connesso: " + clientSocket.getInetAddress());

            clientSockets[this.connectedClients] = clientSocket;
            this.connectedClients++;
        }

        // System.out.println("Entrambi i client sono connessi. Invio il campo
        // iniziale.");

        for (Socket clientSocket : this.clientSockets) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(xml);
            out.flush();

            // l'altro client deve avere la pallina invertita
            Field tmp = xmlService.fromXML(xml);
            if (tmp.getBall().getDirectionX() == 'l') {
                tmp.getBall().setDirectionX('r');
                tmp.getBall().setX(750);
            } else {
                tmp.getBall().setDirectionX('l');
                tmp.getBall().setX(750);
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

    public void updateFields(String[] sortedPlayers, XML xmlService)
            throws IOException, TransformerException, ParserConfigurationException {
        String fieldStr = "";
        String secondFieldStr = "";
        // System.out.println("Server in attesa di connessioni...");

        while (this.connectedClients < 2) {
            Socket clientSocket = this.serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            if ((clientSocket.getInetAddress()+"").equals(sortedPlayers[0])) {
                fieldStr = in.readLine();
            }
            else{
                secondFieldStr = in.readLine();
            }

            clientSockets[this.connectedClients] = clientSocket;
            this.connectedClients++;
        }

        // System.out.println("Entrambi i client sono connessi. Inizio procedura.");

        // riordina campi in base a ordine iniziale di player
       if (!((clientSockets[0].getInetAddress() + "").contains(sortedPlayers[0]))) { // disordinati
            Socket tmpSocket = this.clientSockets[0];
            this.clientSockets[0] = this.clientSockets[1];
            this.clientSockets[1] = tmpSocket;
        }

        // conversione campo
        Field tmpField;
        tmpField = xmlService.fromXML(fieldStr);
        

        // inizio modifica campi
        FieldUpdater fieldUpdater = new FieldUpdater(tmpField);
       
        // controlli vari
        fieldUpdater.controls();

        //invio al player1
        PrintWriter out = new PrintWriter(clientSockets[0].getOutputStream(), true);
        out.println(xmlService.fieldToXML(tmpField));
        out.flush();

        //swap informazioni
        Field secondTmpField = xmlService.fromXML(secondFieldStr);
        fieldUpdater.swapInfo(secondTmpField);
        tmpField = fieldUpdater.getField();

        //invio al player2
        out = new PrintWriter(clientSockets[1].getOutputStream(), true);
        out.println(xmlService.fieldToXML(tmpField));
        out.flush();

        // chiude i socket dei client
        for (Socket clientSocket : this.clientSockets) {
            clientSocket.close();
        }

        // chiude il socket del server
        this.serverSocket.close();
    }

}
