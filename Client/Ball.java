import java.awt.Graphics;
import java.util.Random;

public class Ball {
    private double x;
    private double y;
    private int radius;
    private char directionX;
    private char directionY;
    private int angle;
    //se 0 = nessuno
    //se 1 = player 1
    //se 2 = player 2
    private int lastTouch;

    /*
     * Costruttore
     */
    Ball() {
        this.x = 0;
        this.y = 0;
        this.radius = 25;
        this.directionX = ' ';
        this.directionY = ' ';
        this.angle = 0;
        this.lastTouch = 0;
    }

    /*
     * Disegno pallina
     */
    public void drawBall(Graphics g) {
       // g.setColor(Color.WHITE);
        g.fillOval((int)this.x, (int)this.y, this.radius, this.radius);// DrawCircle(this.x, this.y, this.radius, White, true);
    }

    public void generateBall() {
        Random random = new Random();
        // left o right
        int gen = random.nextInt(0, 2);

        if (gen == 0) { // left
            this.directionX = 'l';
        } else { // right
            this.directionX = 'r';
        }

        // up o down
        gen = random.nextInt(0, 2);

        if (gen == 0) { // left
            this.directionY = 'u';
        } else { // right
            this.directionY = 'd';
        }

        // starting x
        if (this.directionX == 'l') {
            this.x = (1500 / 2) - this.radius;
        } else if (this.directionX == 'r') {
            this.x = (1500 / 2) + this.radius;
        }
        // starting y
        this.y = 750 / 2;

        // starting angle

        // up left (135-180)
        if (this.directionY == 'u' && this.directionX == 'l') {
            gen = random.nextInt(46) + 135;
        }
        // up right (0-45)
        else if (this.directionY == 'u' && this.directionX == 'r') {
            gen = random.nextInt(46);
        }
        // down left (180-225)
        else if (this.directionY == 'd' && this.directionX == 'l') {
            gen = random.nextInt(46) + 180;
        }
        // down right (315-360)
        else if (this.directionY == 'd' && this.directionX == 'r') {
            gen = random.nextInt(46) + 315;
        }

        this.angle = gen;
    }

    public void updateBallCoordinates() {
        double val1 = Math.abs(Math.cos(this.angle));
        double val2 = Math.abs(Math.sin(this.angle));
        // up left
        if (this.directionY == 'u' && this.directionX == 'l') {
            this.x = this.x - val1*10;
            this.y = this.y - val2*10;
        }
        // up right
        else if (this.directionY == 'u' && this.directionX == 'r') {
            this.x = this.x + val1*10;
            this.y = this.y - val2*10;
        }
        // down left
        else if (this.directionY == 'd' && this.directionX == 'l') {
            this.x = this.x - val1*10;
            this.y = this.y + val2*10;
        }
        // down right
        else if (this.directionY == 'd' && this.directionX == 'r') {
            this.x = this.x + val1*10;
            this.y = this.y + val2*10;
        }
    }

    public void updateBallCoordinatesPowerUp() {
        //left
        if (this.directionX == 'l')
            this.x = this.x - 4;
        //right
        else if (this.directionX == 'r') {
            this.x = this.x + 4;
        }
    }

    public void setDirectionXPowerUp(int value) {
        if(value == 1)
            this.directionY = 'l';
        else if(value == 2)
            this.directionY = 'r';
    }

    public void generateBallPowerUp() {
        // left o right in base all'ultimo touch della pallina
        if (this.lastTouch == 1) { // left
            this.directionX = 'l';
        } else if (this.lastTouch == 2) { // right
            this.directionX = 'r';
        }
    }

    /*
     * Get della coordinata x della pallina
     */
    public double getX() {
        return this.x;
    }

    /*
     * Get della coordinata y della pallina
     */
    public double getY() {
        return this.y;
    }

    /*
     * Get ultimo giocatore che ha toccato
     */
    public int getLastTouch() {
        return this.lastTouch;
    }

    /*
     * Get del raggio della pallina
     */
    public int getRadius() {
        return this.radius;
    }

    /*
     * Get della direzione orizzontale della pallina
     */
    public char getDirectionX() {
        return this.directionX;
    }

    /*
     * Get della direzione verticale della pallina
     */
    public char getDirectionY() {
        return this.directionY;
    }

    public int getAngle() {
        return this.angle;
    }

    public void setDirectionX(char value) {
        this.directionX = value;
    }

    public void setDirectionY(char value) {
        this.directionY = value;
    }

    public void setAngle(int value) {
        this.angle = value;
    }

    public void setLastTouch(int touch) {
        this.lastTouch = touch;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    
}
