import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP_CLIENT {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String response;

    public TCP_CLIENT(String ip, int port) throws UnknownHostException, IOException {
        this.clientSocket = new Socket(ip, port);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.response = "";
    }

    public void startGame() throws IOException {
        this.response = in.readLine();
        System.out.println("Messaggio ricevuto: " + response);
        clientSocket.close();
    }


    public String receiveField() throws IOException{
        this.response = in.readLine();
        clientSocket.close();
        return this.response;
    }

    public void sendField(String message) throws IOException {
        out.println(message);
        clientSocket.close();
    }
}
