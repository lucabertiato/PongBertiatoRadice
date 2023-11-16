import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class Main extends JFrame {
    public static void main(String[] args) throws IOException, InterruptedException, TransformerException, ParserConfigurationException {
        boolean start = false;
        TCP_CLIENT tcpService = new TCP_CLIENT("127.0.0.1", 666);
        XML xmlService = new XML("tmp");

        // GUI gui = new GUI('W');
        // gui.creaFinestra(null);
       tcpService.startGame();

        // countdown
       // gui = new GUI('C');
        //gui.creaFinestra(null);
        //Thread.sleep(5000);

        // gioco effettivo
        //ricezione field iniziale da server
        Thread.sleep(5);
        tcpService = new TCP_CLIENT("127.0.0.1", 666);
        String XMLfield = tcpService.receiveFirstField();//new Field();
        Field field = xmlService.fromXML(XMLfield);
      
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

            //aggiorna campo
            tcpService = new TCP_CLIENT("127.0.0.1", 666);
            String newXmlField = tcpService.updateField(xmlService.fieldToXML(field));
            System.out.println(newXmlField);
            Field newField = xmlService.fromXML(newXmlField);
            field.updateField(newField);
           

       
    
          /* field.getBall().updateBallCoordinates();
            if (field.checkWallHit()) {
                field.getBall().generateBall();
            }
            field.checkPaddleHit();
            field.checkPowerUpBlockHit();
            field.checkPowerUpBallHit();

            //aggiorno x e y della pallina power up
            for(int i = 0; i < field.listPowerUp.size(); i++){
                if(field.listPowerUp.get(i).getIsBallPowerUpActivate())
                    field.listPowerUp.get(i).getBallPowerUp().updateBallCoordinatesPowerUp();
            }
            */ 

            //controllo punteggio
            game = field.checkScores();
            Thread.sleep(100);
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