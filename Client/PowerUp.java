import java.awt.Color;
import java.awt.Graphics;

public class PowerUp {
    //quadrato che contiene un potenziamento di diverso tipo
    private int x;
    private int y;
    private int width;
    private char type;
    private Boolean isActivate;
    private String img;

    /*
     * Costruttore (default)
    */
    public PowerUp() {
        this.x = 500;
        this.y = y;
        this.type = ' ';
        this.width = 35;
        this.isActivate = true;
        this.img = "";
    }

    /*
     * Costruttore (con parametri)
     * indica il tipo di potenziamento
     * e il percoso dell'immagine da mettere nel quadrato
     * e la coordinata y
     */
    public PowerUp(char type, String img, int y) {
        this.x = 500;
        this.y = y;
        this.width = 35;
        this.type = type;
        this.isActivate = true;
        this.img = img;
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
     */
    public void setType(char type) {
        //TODO controlli per il tipo di potenziamento
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
}
