import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.awt.event.KeyEvent;
import java.io.IOException;


public class Main extends JFrame {
    public static void main(String[] args) throws IOException, InterruptedException, TransformerException, ParserConfigurationException {
        boolean start = false;
        // connessione
        // TCP_CLIENT tcpService = new TCP_CLIENT("127.0.0.1", 666);
        // GUI gui = new GUI('W');
        // gui.creaFinestra(null);
        // tcpService.startGame();

        // countdown
       // gui = new GUI('C');
        //gui.creaFinestra(null);
        //Thread.sleep(5000);

        // gioco effettivo
        Field field = new Field();
        field.getBall().generateBall();
        GUI gui = new GUI('G', field);
        gui.creaFinestra(field);

        // il main aggiorna l'oggetto campo ogni 10ms mentre nella GUI ogni 10ms viene
        // ridisegnato il campo aggiornato
        int game = 0;
        while (game == 0) {

            int lastKey = gui.getListener().getLastKeyPressed();
            if (lastKey == KeyEvent.VK_W) {
                if (field.checkTop()) {
                    field.getPlayerOne().getPaddle().setY('W');
                }
            } else if (lastKey == KeyEvent.VK_S) {
                if (field.checkDown()) {
                    field.getPlayerOne().getPaddle().setY('S');
                }
            }

            //serialize in xml del campo
            /*XML_CLIENT xmlService = new XML_CLIENT("tmp.xml");
            String xmlField = xmlService.fieldToXML(field);
            TCP_CLIENT tcpService = new TCP_CLIENT("127.0.0.1", 666);
            tcpService.sendField(xmlField);*/
    
            field.getBall().updateBallCoordinates();
            if (field.checkWallHit()) {
                field.getBall().generateBall();
            }
            field.checkPaddleHit();
            field.checkPowerUpHit();
            game = field.checkScores();

            Thread.sleep(10);
        }
        
        gui.stopGame();
        if(game == 1){
            System.out.println("Hai vinto!");
        }
        else if(game == 2){
            System.out.println("Hai perso!");
        }
    }
}