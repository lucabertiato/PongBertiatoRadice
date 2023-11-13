import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_SERVER {
    ServerSocket serverSocket;
    Socket[] clientSockets;
    int connectedClients;

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
     * @throws IOException
     */
    public void startGame() throws IOException {
        System.out.println("Server in attesa di connessioni...");

        while (connectedClients < 2) {
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("Client connesso: " + clientSocket.getInetAddress());

            clientSockets[this.connectedClients] = clientSocket;
            this.connectedClients++;
        }

        System.out.println("Entrambi i client sono connessi. Invio il messaggio di avvio.");

        for (Socket clientSocket : clientSockets) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("AVVIO");
        }

        //chiude i socket dei client
        for (Socket clientSocket : clientSockets) {
            clientSocket.close();
        }

        //chiude il socket del server
        this.serverSocket.close();
    }
}
