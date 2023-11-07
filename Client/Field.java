import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Field {
    Player playerOne;
    Player playerTwo;
    Ball ball;

    /*
     * Costruttore
     */
    public Field() {
        this.playerOne = new Player();
        this.playerTwo = new Player(1500 - (this.playerOne.getPaddle().getX()) - 25);
        this.ball = new Ball();
    }

    /*
     * Disegno campo da gioco
     */
    public void drawField(Graphics g) {
        g.setColor(Color.WHITE);
        // racchette
        this.playerOne.getPaddle().drawPaddle(g);
        this.playerTwo.getPaddle().drawPaddle(g);

        // palla
        this.ball.drawBall(g);

        // punteggio
        // Crea una nuova fonte con dimensione personalizzata
        Font customFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(customFont);
        g.drawString(this.playerOne.getScore() + "", (1500 / 2) - 45, 50);
        g.drawString(this.playerTwo.getScore() + "", (1500 / 2) + 20, 50);

        // metà campo
        for (int i = 0; i < 750; i += 20) {
            // g.setColor(Color.WHITE);
            g.drawLine(1500 / 2, i, 1500 / 2, i + 10);
        }

    }

    /*
     * Get del primo giocatore
     */
    public Player getPlayerOne() {
        return this.playerOne;
    }

    /*
     * Get del secondo giocatore
     */
    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    /*
     * Get della pallina
     */
    public Ball getBall() {
        return this.ball;
    }

    /*
     * Richiamo del controllo del colpo della palla a una parete
     */
    public boolean checkWallHit() {
        // controllo pareti orizzontali
        if (this.ball.getY() <= 0 || this.ball.getY() + (this.ball.getRadius() * 2) >= 750) {
            if (this.ball.getDirectionY() == 'u') {
                this.ball.setDirectionY('d');
            } else if (this.ball.getDirectionY() == 'd') {
                this.ball.setDirectionY('u');
            }
        }

        // controllo pareti verticali
        if (this.ball.getX() <= 0 || this.ball.getX() + (this.ball.getRadius() * 2) >= 1500) {
            if (this.ball.getX() <= 0) {
                this.playerTwo.increaseScore();
            } else {
                this.playerOne.increaseScore();
            }
            return true;
        }
        return false;
    }

    public void checkPaddleHit() {
        // controllo paddle avversaria
        if ((this.ball.getX() + (this.ball.getRadius() * 2) >= this.playerTwo.getPaddle().getX())
                && (this.ball.getY() >= this.playerTwo.getPaddle().getY() && this.ball
                        .getY() <= this.playerTwo.getPaddle().getY() + this.playerTwo.getPaddle().getHeight())) {
            // arriva dal basso
            if (this.ball.getDirectionY() == 'u') {
                this.ball.setAngle(this.ball.getAngle() + 90);
            }
            // arriva dall'alto
            else if (this.ball.getDirectionY() == 'd') {
                this.ball.setAngle(this.ball.getAngle() - 90);
            }
            // inverte percorso sul piano x
            this.ball.setDirectionX('l');
        }
        // controllo paddle utente
        else if ((this.ball.getX() >= this.playerOne.getPaddle().getX()
                && this.ball.getX() <= this.playerOne.getPaddle().getX() + this.playerOne.getPaddle().getWidth())
                && (this.ball.getY() >= this.playerOne.getPaddle().getY() && this.ball
                        .getY() <= this.playerOne.getPaddle().getY() + this.playerOne.getPaddle().getHeight())) {
            // arriva dal basso
            if (this.ball.getDirectionY() == 'u') {
                this.ball.setAngle(this.ball.getAngle() - 90);
            }
            // arriva dall'alto
            else if (this.ball.getDirectionY() == 'd') {
                this.ball.setAngle(this.ball.getAngle() + 90);
            }
            // inverte percorso sul piano x
            this.ball.setDirectionX('r');
        }
    }

}
