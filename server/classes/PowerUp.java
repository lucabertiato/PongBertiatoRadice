import java.awt.Graphics;
import java.util.Random;

public class PowerUp {
    //quadrato che contiene un potenziamento di diverso tipo
    private int x;
    private int y;
    private int width;
    private char type;
    //pallina che esce dopo aver colpito il blocco
    private Ball ballPowerUp;
    private Boolean isBallActivate;
    private long timeCreate;

   /**
     * Costruttore di default
     */
    public PowerUp() {
        this.x = 500;
        this.y = 0;
        this.type = ' ';
        this.width = 35;
        this.ballPowerUp = new Ball();
        this.isBallActivate = false;
        this.timeCreate = System.currentTimeMillis();
    }

    /**
     * Costruttore con parametri coordinate
     * @param y y del PowerUp
     * @param x x del PowerUp
     */
    public PowerUp(int y, int x) {
        this.x = x;
        this.y = y;
        this.width = 35;
        this.type = ' ';
        this.ballPowerUp = new Ball();
        this.isBallActivate = false;
        this.timeCreate = System.currentTimeMillis();
    }

    public PowerUp(int y, int x, char type) {
        this.x = x;
        this.y = y;
        this.width = 35;
        this.type = type;
        this.ballPowerUp = new Ball();
        this.isBallActivate = false;
        this.timeCreate = System.currentTimeMillis();
    }

    
    /**
     * Disegno del blocco del PowerUp
     * @param g graphics
     */
    public void drawPowerUp(Graphics g) {
        g.fillRect(this.x, this.y, this.width, this.width);
    }

    /**
     * Set coordinate pallina del PowerUp
     * @param x x della pallina
     * @param y y della pallina
     */
    public void setBallPowerUpCoordinates(int x, int y){
        this.ballPowerUp.setX(x);
        this.ballPowerUp.setY(y);
    }

   /**
    * Disegno della pallina del potenziamento
    * @param g graphics
    */
    public void drawBallPowerUp(Graphics g){
        if(this.isBallActivate)
            this.ballPowerUp.drawBall(g, 'b');
    }

    /**
     * Get della coordinata x
     * @return x coordinata x del power up
     */
    public int getX() {
        return x;
    }

    /**
     * Set della coordinata X
     * @param x coordinata x del power up
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set dell'ora di creazione
     * @param x nuovo valore
     */
    public void setTimeCreate(Long time) {
        this.timeCreate = time;
    }

    /**
     * Get dell'ora di creazione
     * @return ora di creazione
     */
    public long getTimeCreate() {
        return this.timeCreate;
    }

    /**
     * Get della coordinata y
     * @return y coordinata y del power up
     */
    public int getY() {
        return y;
    }

    /**
     * Set della coordinata y 
     * @param y coordinata y del power up
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get della pallina del PowerUp
     * @return ballPowerUp palla power up
     */
    public Ball getBallPowerUp(){
        return this.ballPowerUp;
    }
    
     /**
     * Get della lunghezza
     * @return width lunghezza quadrato power up
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set della lunghezza del quadrato del power up
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get del tipo di PowerUp
     * @return type tipo di power up
     */
    public char getType() {
        return type;
    }

    /**
     * Set tipo PowerUp
     * @param type nuovo tipo del power up (A, o B)
     */
    public void setType(char type) {
        //TODO in base al carattere metto anche l'icona del potenziamento
        if(type == 'A' || type == 'B' || type == 'C' || type == 'D')
            this.type = type;
    }

    /**
     * Get dello stato della pallinadel power up
     * @return stato della pallina (esistente o meno)  
     */
    public Boolean getIsBallPowerUpActivate() {
        return this.isBallActivate;
    }
    /**
     * Set dello stato della pallina del power up
     * @param isBallActivate nuovo stato della pallina (esistente o meno)  
     */
    public void setIsBallPowerUpActivate(Boolean isBallActivate) {
        this.isBallActivate = isBallActivate;
    }    

    public void checkTimeCreate(){
        if(System.currentTimeMillis() - this.timeCreate > 10000)
            this.movePowerUp();
    }

    public void movePowerUp(){
        Random random = new Random();
        this.x = random.nextInt(900) + 200;
        this.y = random.nextInt(650) + 50;
        this.timeCreate = System.currentTimeMillis();
    }
}
