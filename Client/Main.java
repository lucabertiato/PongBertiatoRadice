import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class Main extends JFrame {
    public static void main(String[] args) throws IOException, InterruptedException {
        boolean start = false;
        // connessione
        TCP_CLIENT tcpService = new TCP_CLIENT("127.0.0.1", 666);
        GUI gui = new GUI('W');
        gui.creaFinestra(null);
        tcpService.startGame();

        // countdown
       // gui = new GUI('C');
        //gui.creaFinestra(null);
        //Thread.sleep(5000);

        // gioco effettivo
        Field field = new Field();
        field.getBall().generateBall();
        gui = new GUI('G');
        gui.creaFinestra(field);

        // il main aggiorna l'oggetto campo ogni 10ms mentre nella GUI ogni 10ms viene
        // ridisegnato il campo aggiornato
        boolean game = true;
        while (game) {

            int lastKey = gui.getListener().getLastKeyPressed();

            if (lastKey == KeyEvent.VK_W) {
                if (field.checkTop()) {
                    field.getPlayerOne().getPaddle().setY('W');
                }
            } else if (lastKey == KeyEvent.VK_S) {
                if (field.checkDown()) {
                    field.getPlayerOne().getPaddle().setY('S');
                }
            } else if (lastKey == KeyEvent.VK_A) {
                field.getPlayerTwo().getPaddle().setY('W');
            } else if (lastKey == KeyEvent.VK_D) {
                field.getPlayerTwo().getPaddle().setY('S');
            }

            field.getBall().updateBallCoordinates();
            if (field.checkWallHit()) {
                field.getBall().generateBall();
            }
            field.checkPaddleHit();
            game = field.checkScores();

            Thread.sleep(10);
        }

    }
}