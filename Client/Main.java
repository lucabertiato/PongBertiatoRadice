import javax.swing.*;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main extends JFrame {
    public static void main(String[] args) throws IOException, InterruptedException {
        Field field = new Field();
        field.getBall().generateBall();
        GUI gui = new GUI();
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
            }
            else if (lastKey == KeyEvent.VK_S) {
                if (field.checkDown()) {
                    field.getPlayerOne().getPaddle().setY('S');
                }
            }
            else if (lastKey == KeyEvent.VK_A) {
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

        /*
         * bool game = true;
         * 
         * while (game) {
         * Clear();
         * Wait(10);
         * char key = LastKey();
         * //player 1
         * if (key == 'w' || key == 's') {
         * field.getPlayerOne().getPaddle().setY(key);
         * }
         * //player 2
         * else if(key == 'i'){
         * field.getPlayerTwo().getPaddle().setY('w');
         * }
         * else if (key == 'k') {
         * field.getPlayerTwo().getPaddle().setY('s');
         * }
         * field.getBall().updateBallCoordinates();
         * field.checkWallHit();
         * field.checkPaddleHit();
         * field.drawField();
         * Present();
         * }
         */

    }
}