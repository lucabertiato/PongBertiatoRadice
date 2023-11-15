import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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

            tmp[this.connectedClients] = clientSocket.getInetAddress()+"";
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

    public void sendToPlayer(String IP, String xml) throws UnknownHostException, IOException{
        this.serverSocket = new ServerSocket(666);
        Socket clientSocket = new Socket(IP, 666);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(xml);
        clientSocket.close();
        serverSocket.close();
    }

    public String[] receiveFields() throws IOException {
        String[] array = new String[2];

        while (this.connectedClients < 2) {
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("Client connesso: " + clientSocket.getInetAddress());

            clientSockets[this.connectedClients] = clientSocket;
            

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String field = in.readLine();
            array[this.connectedClients] = clientSocket.getInetAddress() + ";" + field;

            this.connectedClients++;
        }

        // Chiudi il socket del server
        serverSocket.close();

        return array;

    }

    
}
