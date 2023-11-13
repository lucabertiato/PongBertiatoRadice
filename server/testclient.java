import java.io.*;
import java.net.*;

public class testclient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 666); // Sostituisci con l'indirizzo IP del tuo server e la porta desiderata

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String response = in.readLine();
            System.out.println("Messaggio ricevuto: " + response);

            // Chiudi il socket del client
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}