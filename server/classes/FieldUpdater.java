import java.util.ArrayList;
import java.util.List;

public class FieldUpdater {
    private Field f;

    /**
     * Costruttore con parametri
     * @param f1 campo giocatore 1
     * @param f2 campo giocatore 2
     */
    public FieldUpdater(Field f) {
        this.f = f;
    }

    /**
     * Scambia le inforamzioni fra i due campi per la visualizzazione a specchio
     * @param f2 campo da cui prendere i valori
     */
    public void swapInfo(Field f2) {
        Field tmp = new Field();

        tmp.setPlayerOne(f2.getPlayerOne());
        tmp.setPlayerTwo(this.f.getPlayerOne());

        tmp.getPlayerOne().setScore(this.f.getPlayerTwo().getScore());
        tmp.getPlayerTwo().setScore(this.f.getPlayerOne().getScore());

        tmp.getPlayerOne().setSets(this.f.getPlayerTwo().getSets());
        tmp.getPlayerTwo().setSets(this.f.getPlayerOne().getSets());

        tmp.getPlayerOne().getPaddle().setX(1500-f2.getPlayerTwo().getPaddle().getX()-this.f.getPlayerTwo().getPaddle().getWidth());
        tmp.getPlayerTwo().getPaddle().setX(1500-this.f.getPlayerOne().getPaddle().getX()-this.f.getPlayerOne().getPaddle().getWidth());

        tmp.setBall(this.f.getBall());
        tmp.getBall().setX(1500-this.f.getBall().getX());

        this.f = tmp;
    }

    /**
     * controlli della posizione dalla pallina dopo un moviemnto
     */
    public void controls() {
        boolean generateBall = false;

        this.f.getBall().updateBallCoordinates();
        if (this.f.checkWallHit()) {
            generateBall = true;
        } else {
            this.f.checkPaddleHit();
        }

        if (generateBall) {
            Ball tmpBall = new Ball();
            tmpBall.generateBall();
            this.f.setBall(tmpBall);
        }
    }

    /**
     * Get del campo aggiornato
     * @return campo aggiornato
     */
    public Field getField() {
        return this.f;
    }

}
