import java.io.*;
import java.net.*;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Server {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
   
        //avvio
        TCP_SERVER tcpService = new TCP_SERVER();
        String[] arrayIPs = tcpService.startGame();

        //gioco
        //campo iniziale per generazione palla
        Field field = new Field();
        field.getBall().generateBall();

        //invio ai giocatori del campo
        XML xmlService = new XML("tmp");
        tcpService.sendToPlayer(arrayIPs[0].replaceAll("/", ""), xmlService.fieldToXML(field));
        //da fare inversione a specchio

        tcpService.sendToPlayer(arrayIPs[1].replaceAll("/", ""), xmlService.fieldToXML(field));


        int game = 0;
       // while(game == 0){
       /*  tcpService = new TCP_SERVER();
        String[] array = tcpService.receiveFields();

        //suddivisione ip e campi
        XML_SERVER xmlService = new XML_SERVER("tmp");
        String[] arrayIPs = new String[2];
        String[] arrayXMLFields = new String[2];
        Field[] fields = new Field[2];

        for(int i = 0; i < 2; i++){
            String[] tmp = array[i].split(";");
            arrayIPs[i] = tmp[0];
            arrayXMLFields[i] = tmp[1];
            fields[i] = xmlService.fromXML(arrayXMLFields[i]);
        }
        */
        System.out.println();

        //converto in oggetto
        //}
    }
}