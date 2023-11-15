import java.awt.Graphics;

public class PowerUp {
    //quadrato che contiene un potenziamento di diverso tipo
    private int x;
    private int y;
    private int width;
    private char type;
    private Boolean isActivate;
    private String img;
    private Boolean isValid;
    //pallina che esce dopo aver colpito il blocco
    private Ball ballPowerUp;
    private Boolean isBallActivate;

    /*
     * Costruttore (default)
    */
    public PowerUp() {
        this.x = 500;
        this.y = 0;
        this.type = ' ';
        this.width = 35;
        this.isActivate = false;
        this.img = "";
        this.isValid = false;
        this.ballPowerUp = new Ball();
        this.isBallActivate = false;
    }

    /*
     * Costruttore (con parametri)
     * con la definizione delle coordinate
     */
    public PowerUp(int y, int x) {
        this.x = x;
        this.y = y;
        this.width = 35;
        this.type = ' ';
        this.isActivate = false;
        this.img = "";
        this.isValid = false;
        this.ballPowerUp = new Ball();
        this.isBallActivate = false;
    }

    /*
     * Disegno il quadrato del potenziamento
     * disegna solo se il parametro visualizza è true
     */
    public void drawPowerUp(Graphics g) {
        if(this.isActivate)
            g.fillRect(this.x, this.y, this.width, this.width);
    }

    /*
     * Set coordinate pallina potenziamento
     */
    public void setBallPowerUpCoordinates(int x, int y){
        this.ballPowerUp.setX(x);
        this.ballPowerUp.setY(y);
    }

    /*
     * Disegno la pallina del potenziamento
     */
    public void drawBallPowerUp(Graphics g){
        if(this.isBallActivate)
            this.ballPowerUp.drawBall(g, 'b');
    }

    /*
     * Get della coordinata x 
     */
    public int getX() {
        return x;
    }
    /*
     * Set della coordinata x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /*
     * Get della coordinata y 
     */
    public int getY() {
        return y;
    }
    /*
     * Set della coordinata y 
     */
    public void setY(int y) {
        this.y = y;
    }

    public Ball getBallPowerUp(){
        return this.ballPowerUp;
    }
    
    /*
     * get della grandezza del quadrato
     */
    public int getWidth() {
        return width;
    }
    /*
     * set della grandezza del quadrato
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /*
     * get tipo power up
     */
    public char getType() {
        return type;
    }
    /*
     * set tipo power up con controlli
     *
    * CARATTERI POSSIBILI
    * A
    * B
    * C
    * D
    */
    public void setType(char type) {
        //TODO in base al carattere metto anche l'icona del potenziamento
        if(type == 'A' || type == 'B' || type == 'C' || type == 'D')
            this.type = type;
    }

    /*
     * get stato del potenziamento
     */
    public Boolean getIsActivate() {
        return isActivate;
    }
    /*
     * set stato del potenziamento
     */
    public void setIsActivate(Boolean isActivate) {
        this.isActivate = isActivate;
    }

    /*
     * get stato della pallina del potenziamento
     */
    public Boolean getIsBallPowerUpActivate() {
        return this.isBallActivate;
    }
    /*
     * set stato della pallina del potenziamento
     */
    public void setIsBallPowerUpActivate(Boolean isBallActivate) {
        this.isBallActivate = isBallActivate;
    }


    /*
     * get stato del potenziamento
     */
    public Boolean getIsValid() {
        return isValid;
    }
    /*
     * set stato del potenziamento
     */
    public void setIsValid(Boolean isValid) {
        this.isActivate = isValid;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
}