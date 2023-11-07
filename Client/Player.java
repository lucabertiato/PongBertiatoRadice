public class Player {
    private Paddle paddle;
    private int score;

    /*
     * Costruttore (default per il player locale)
     */
    public Player() {
        this.paddle = new Paddle();
        this.score = 0;
    }

    /*
     * Costruttore (con parametri per il secondo player che avrà la racchetta
     * dall'altra parte del campo)
     */
    public Player(int x) {
        this.paddle = new Paddle(x);
        this.score = 0;
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

}
