import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class Main extends JFrame {
    public static void main(String[] args) throws IOException, InterruptedException, TransformerException, ParserConfigurationException {
        TcpClient tcpService = new TcpClient("localhost", 667);
        XML xmlService = new XML("tmp");

        GUI gui = new GUI('W', null, 0);
        gui.creaFinestra(null);
        tcpService.startGame();
        gui.chiudiFinestra();


        // gioco effettivo
        //ricezione field iniziale da server
        Thread.sleep(5);
        tcpService = new TcpClient("localhost", 667);
        String XMLfield = tcpService.receiveFirstField();
        Field field = xmlService.fromXML(XMLfield);
      
        gui = new GUI('G', field, 0);
        gui.creaFinestra(field);

        // il main aggiorna l'oggetto campo ogni 10ms mentre nella GUI ogni 10ms viene
        // ridisegnato il campo aggiornato
        int game = 0;

        Thread updateThread = new Thread(new ThreadUpdate(field));
        updateThread.start();
        
     
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

            //controllo punteggio
            game = field.checkScores();
            Thread.sleep(100);
        }
        
        gui.stopGame();

        gui = new GUI('E', null, game);
        gui.creaFinestra(field);
        Thread.sleep(5000);
        gui.stopGame();
    }
}