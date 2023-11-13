import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Server {
    public static void main(String[] args) throws IOException {
        TCP_SERVER tcpService = new TCP_SERVER();

        //avvio gioco
        tcpService.startGame();
    }
}