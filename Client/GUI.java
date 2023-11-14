import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI {
    private static int WIDTH = 1500;
    private static int HEIGHT = 750;

    private JFrame finestra;
    private Listener keyListener;
    private Timer gameTimer;
    private Timer powerUpTimer;

    private char type; // W = waiting; C = countdown; G = game;

    private Timer countdownTimer;
    private int second; //per il countdown

    public GUI(char type, Field f) throws IOException {
        this.type = type;
        this.second = 5;

        finestra = new JFrame("Pong");
        if (type == 'G') {
            this.keyListener = new Listener();
            finestra.addKeyListener(keyListener);
            finestra.setFocusable(true);

            gameTimer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    finestra.repaint();
                }
            });
            gameTimer.start();

            powerUpTimer = new Timer(10000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.generatePowerUp();
                }
            });
            powerUpTimer.start();
        }
        else if(type == 'C'){
            this.countdownTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    finestra.repaint();
                }
                
            });
            this.countdownTimer.start();

            if(this.second == 0){
                this.countdownTimer.stop();
            }
        }
    
    }

    public void stopGame(){
        this.gameTimer.stop();
        this.finestra.dispose();
    }

    public void creaFinestra(Field f) {
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        finestra.getContentPane().setBackground(Color.BLACK);
        if (this.type == 'G') {
            finestra.add(new GamePanel(f));
        } else {
            boolean startGame;
            if(this.type == 'W'){
                startGame = false;
            }
            else{
                startGame = true;
            }
            finestra.add(new LoadingPanel(startGame, this.second));
        }

        finestra.pack();
        finestra.setVisible(true);
    }

    public Listener getListener() {
        return this.keyListener;
    }

    public JFrame getFrame() {
        return this.finestra;
    }
}