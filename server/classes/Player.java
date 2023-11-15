public class Player {
    private Paddle paddle;
    private int score;
    private int sets;
    private PowerUp currentPowerUp;

    /*
     * Costruttore (default per il player locale)
    */
    public Player() {
        this.paddle = new Paddle();
        this.score = 0;
        this.sets = 0;
        this.currentPowerUp = new PowerUp();
    }

    /*
     * Costruttore (con parametri per il secondo player che avrà la racchetta
     * dall'altra parte del campo)
     */
    public Player(int x) {
        this.paddle = new Paddle(x);
        this.score = 0;
        this.sets = 0;
    }

    /*
     * Set del powerUp che si ha a disposizione
     */
    public void setCurrentPowerUp(PowerUp pUp){
        this.currentPowerUp = pUp;
    }

    /*
     * Get del powerUp che si ha a disposizione
     */
    public PowerUp getCurrentPowerUp(){
        return this.currentPowerUp;
    }

    /*
     * Get della racchetta
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /*
     * Get del punteggio
     */
    public int getScore() {
        return this.score;
    }

    /*
     * Incremento punteggio
     */
    public void increaseScore() {
        this.score++;
    }

    /**
     * Get dei set vinti dal giocatore
     * @return set vinti
     */
    public int getSets(){
        return this.sets;
    }

    public void increaseSets(){
        this.sets++;
    }

    public void resetScore(){
        this.score = 0;
    }

    public void setPaddle(Paddle p){
        this.paddle = p;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    

}
