import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(666); // Porta del server

            Socket[] clientSockets = new Socket[2];
            int connectedClients = 0;

            System.out.println("Server in attesa di connessioni...");

            while (connectedClients < 2) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connesso: " + clientSocket.getInetAddress());

                clientSockets[connectedClients] = clientSocket;
                connectedClients++;
            }

            System.out.println("Entrambi i client sono connessi. Invio il messaggio di avvio.");

            for (Socket clientSocket : clientSockets) {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("avvio");
            }

            // Chiudi i socket dei client
            for (Socket clientSocket : clientSockets) {
                clientSocket.close();
            }

            // Chiudi il socket del server
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}