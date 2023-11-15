import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Field{
    Player playerOne;
    Player playerTwo;
    Ball ball;
    ArrayList<PowerUp> listPowerUp;

    /*
     * Costruttore
     */
    public Field() {
        this.playerOne = new Player();
        this.playerTwo = new Player(1500 - (this.playerOne.getPaddle().getX()) - 25);
        this.ball = new Ball();
        this.listPowerUp = new ArrayList<PowerUp>();
        //riempi il vettore di powerUp
        this.fillListPowerUp();
    }

    /*
     * riempi il vettore con tutti e 4 i possibili power up
     */
    public void fillListPowerUp(){
        //aggiugni powerup a sinistra
        PowerUp pA = new PowerUp(75, 500);
        PowerUp pB = new PowerUp(250, 500);
        PowerUp pC = new PowerUp(425, 500);
        PowerUp pD = new PowerUp(600, 500);
        this.listPowerUp.add(pA);
        this.listPowerUp.add(pB);
        this.listPowerUp.add(pC);
        this.listPowerUp.add(pD);

        //power up a destra
        PowerUp pA1 = new PowerUp(75, 900);
        PowerUp pB2 = new PowerUp(250, 900);
        PowerUp pC3 = new PowerUp(425, 900);
        PowerUp pD4 = new PowerUp(600, 900);
        this.listPowerUp.add(pA1);
        this.listPowerUp.add(pB2);
        this.listPowerUp.add(pC3);
        this.listPowerUp.add(pD4);
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
        this.ball.drawBall(g, ' ');

        // punteggio
        // crea una nuova fonte con dimensione personalizzata
        Font customFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(customFont);
        g.drawString(this.playerOne.getScore() + "", (1500 / 2) - 55, 50);
        g.drawString(this.playerTwo.getScore() + "", (1500 / 2) + 30, 50);
        // set
        g.setColor(Color.RED);
        g.drawString(this.playerOne.getSets() + "", (1500 / 2) - 85, 50);
        g.drawString(this.playerTwo.getSets() + "", (1500 / 2) + 65, 50);

        // metà campo
        g.setColor(Color.white);
        for (int i = 0; i < 750; i += 20) {
            g.drawLine(1500 / 2, i, 1500 / 2, i + 10);
        }

        //disegna vettore powerup
        //controllo nella funzione
        for (PowerUp powerUp : this.listPowerUp) {
            powerUp.drawPowerUp(g);
        }

        //disegna vettore palline powerup
        //controllo nella funzione
        for(int i = 0; i < this.listPowerUp.size(); i++){
            this.listPowerUp.get(i).drawBallPowerUp(g);
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

    public ArrayList<PowerUp> getListPowerUp(){
        return this.listPowerUp;
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
                //resetto last touch della pallina
                this.ball.setLastTouch(0);
            } else {
                this.playerOne.increaseScore();
                //resetto last touch della pallina
                this.ball.setLastTouch(0);
            }
            return true;
        }
        return false;
    }

    public void checkPowerUpBallHit() {
        if(this.ball.getLastTouch() == 1){
            
        }
    }

    /*
     * controllo se la pallina ha colpito una racchetta
     * return true se l'ha colpita
     * return false se non ha colpito nulla
     */
    public Boolean checkPaddleHit() {
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
            //imposto chi ha fatto l'ultimo tocco
            this.ball.setLastTouch(2);
            return true;
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
            //imposto nella pallina chi ha fatto l'ultimo tocco
            this.ball.setLastTouch(1);
            return true;
        }
        return false;
    }

    /**
     * controllo colpo della pallina sul blocco del potenziamento
     * @param g per disegnare la pallina powerUp
     */
    public void checkPowerUpBlockHit(){
        //per tutti i power up
        for(int i = 0; i < this.listPowerUp.size(); i++){
            //solo se è disponibile
            if(this.listPowerUp.get(i).getIsActivate()){
                //controllo della collisione tra pallina e quadrato del power up
                if ((this.ball.getX() + this.ball.getRadius() >= this.listPowerUp.get(i).getX() && 
                this.ball.getX() + this.ball.getRadius() <= this.listPowerUp.get(i).getX() + this.listPowerUp.get(i).getWidth())
                && (this.ball.getY() + this.ball.getRadius() >= this.listPowerUp.get(i).getY() && 
                this.ball.getY() + this.ball.getRadius() <= this.listPowerUp.get(i).getY() + this.listPowerUp.get(i).getWidth())
                ) {
                    //se la pallina non è stata toccata da nessun giocatore e tocca un power non succede nulla
                    //quindi
                    //se last touch != 0
                    if(this.ball.getLastTouch() != 0){
                        //lo rendo non più disponibile e attivo l'effetto/potenziamento
                        this.listPowerUp.get(i).setIsActivate(false);
                        //il blocco è stato rotto quindi imposto le coordinate dela pallina
                        //lo rendo attivo
                        this.listPowerUp.get(i).setIsBallPowerUpActivate(true);
                        this.listPowerUp.get(i).getBallPowerUp().setDirectionXPowerUp(this.ball.getLastTouch());
                        this.listPowerUp.get(i).setBallPowerUpCoordinates(this.listPowerUp.get(i).getX(), this.listPowerUp.get(i).getY());                     
                    }
                }
            }
        }
    }

    /*
     * Controllo del tocco in alto
     */
    public boolean checkTop() {
        // controllo alto
        if (this.playerOne.getPaddle().getY() - 10 < 0) {
            return false;
        }
        return true;
    }

    /*
     * Controllo del tocco in basso
     */
    public boolean checkDown() {
        // controllo basso
        if (this.playerOne.getPaddle().getY() + 10 > 550) {
            return false;
        }
        return true;
    }

    /*
     * Controllo punteggio
     */
    public int checkScores(){
        boolean tmp = false;

        //controllo set
        //assegna set al giocatore 1
        if(this.playerOne.getScore() == 10 ){
            this.playerOne.increaseSets();
            tmp = true;
        }
        //assegna set al giocatore 2
        else if(this.playerTwo.getScore() == 10){
            this.playerTwo.increaseSets();
            tmp = true;
        }

        //se è finito un set resetta il punteggio
        if(tmp){
            this.playerOne.resetScore();
            this.playerTwo.resetScore();
        }

        //controllo fine gioco
        if(playerOne.getSets() == 3){
            return 1;
        } else if(playerTwo.getSets() == 3){
            return 2;
        }
        return 0;
    }

    /*
     * genera la posizione e la tipologia del power up
     */
    public void generatePowerUp(){
        Random random = new Random();
        int type = random.nextInt(3);
        int pos = random.nextInt(7);
        //attiva il power up alla posizione random
        switch (type) {
            case 0:
                this.listPowerUp.get(pos).setType('A');
                break;
            case 1:
                this.listPowerUp.get(pos).setType('B');
                break;
            case 2:
                this.listPowerUp.get(pos).setType('C');
                break;
            case 3:
                this.listPowerUp.get(pos).setType('D');
                break;
        }
        //lo rendo visibile
        this.listPowerUp.get(pos).setIsActivate(true);
    }

    /*
     * Set giocatore 1
     */
    public void setPlayerOne(Player p){
        this.playerOne = p;
    }

    /*
     * Set giocatore 2
     */
    public void setPlayerTwo(Player p){
        this.playerTwo = p;
    }

    /*
     * Set pallina
     */
    public void setBall(Ball b){
        this.ball = b;
    }

    /*
     * Set lista di powerUp
     */
    public void setListPowerUp(ArrayList<PowerUp> list){
        this.listPowerUp = list;
    }
}