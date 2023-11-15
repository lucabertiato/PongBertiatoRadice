import java.awt.Graphics;

public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;

    /*
     * Costruttore (default)
     */
    public Paddle() {
        this.width = 25;
        this.height = 150;
        this.x = 25;
        this.y = (500/2)-(this.height/2);
    }

    /*
     * Costruttore (con parametri x e y per il secondo player)
     */
    public Paddle(int x) {
        this.width = 25;
        this.height = 150;
        this.x = x;
        this.y = (500/2)-(this.height/2);
    }

    /*
     * Disegno racchetta
     */
    public void drawPaddle(Graphics g) {
       // g.setColor(Color.WHITE);
        g.fillRect(this.x, this.y, this.width, this.height);// DrawRectangle(this.x, this.y, this.width, this.height, White, true);
    }

    /*
     * Spostamento racchetta
     */
    public void setY(char direction) {
        if (direction == 'W') { // alto
            this.y -= 10;
        } else if (direction == 'S') { // basso
            this.y += 10;
        }
    }

    /*
     * Get della coordinata x
     */
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    

}
