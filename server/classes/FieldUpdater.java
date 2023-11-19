import java.util.ArrayList;
import java.util.List;

public class FieldUpdater {
    private List<Field> fields;

    /**
     * Costruttore
     * 
     * @param f1 campo giocatore 1
     * @param f2 campo giocatore 2
     */
    public FieldUpdater(Field f1, Field f2) {
        this.fields = new ArrayList<>();
        this.fields.add(f1);
        this.fields.add(f2);
    }

    //scambia le informazioni fra i due campi
    public void swapInfo() {
        if((this.fields.get(0).getPlayerOne().getScore() > 0) || (this.fields.get(0).getPlayerTwo().getScore() > 0) || (this.fields.get(1).getPlayerOne().getScore() > 0) || (this.fields.get(1).getPlayerTwo().getScore() > 0)){
            System.out.println("");
        }
        this.fields.get(0).getPlayerTwo().getPaddle().setY(this.fields.get(1).getPlayerOne().getPaddle().getY());
        this.fields.get(1).getPlayerTwo().getPaddle().setY(this.fields.get(0).getPlayerOne().getPaddle().getY());

        this.fields.get(0).getPlayerTwo().setScore(this.fields.get(1).getPlayerOne().getScore());
        this.fields.get(1).getPlayerTwo().setScore(this.fields.get(0).getPlayerOne().getScore());

        this.fields.get(0).getPlayerTwo().setSets(this.fields.get(1).getPlayerOne().getSets());
        this.fields.get(1).getPlayerTwo().setSets(this.fields.get(0).getPlayerOne().getSets());
    }

    public void controls() {
        boolean generateBall = false;

        for (int i = 0; i < this.fields.size(); i++) {
            this.fields.get(i).getBall().updateBallCoordinates();
            if (this.fields.get(i).checkWallHit()) {
                generateBall = true;
            }
            this.fields.get(i).checkPaddleHit();
            this.fields.get(i).checkPowerUpBlockHit();
            this.fields.get(i).checkPowerUpBallHit();

            // aggiorno x e y della pallina power up
            for (int j = 0; i < this.fields.get(j).listPowerUp.size(); j++) {
                if (this.fields.get(i).listPowerUp.get(j).getIsBallPowerUpActivate()){
                    this.fields.get(i).listPowerUp.get(j).getBallPowerUp().updateBallCoordinatesPowerUp();
                }
            }
        }

        if(generateBall){
            Ball tmpBall = new Ball();
            tmpBall.generateBall();
            this.fields.get(0).setBall(tmpBall);

            //inverte pallina per secondo campo
            if (tmpBall.getDirectionX() == 'l') {
                tmpBall.setDirectionX('r');
            } else {
                tmpBall.setDirectionX('l');
            }
            this.fields.get(1).setBall(tmpBall);
        }
    }

    public Field getFieldOne(){
        return this.fields.get(0);
    }

    public Field getFieldTwo(){
        return this.fields.get(1);
    }
}
