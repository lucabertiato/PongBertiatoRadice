import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Server {
    public static void main(String[] args) throws IOException {
    //    TCP_SERVER tcpService = new TCP_SERVER();

        //avvio gioco
   //     tcpService.startGame();

        //gioco effettivo
        int game = 0;
       // while(game == 0){
        TCP_SERVER tcpService = new TCP_SERVER();
        String xmlString = tcpService.receiveField();


        //converto in oggetto
        XML_SERVER xmlService = new XML_SERVER("tmp");
        Field campo = xmlService.fromXML(xmlString);
        System.out.println();
        //}
    }
}