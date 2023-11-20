import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class GUI {
    private static int WIDTH = 1500;
    private static int HEIGHT = 750;

    private JFrame finestra;
    private Listener keyListener;
    private Timer gameTimer;

    private char type; // W = waiting; G = game;
    private int second;
    private int result;

    public GUI(char type, Field f, int result) throws IOException {
        this.type = type;
        this.second = 5;
        this.result = result;

        finestra = new JFrame("Pong");
        if (type == 'G') {
            this.keyListener = new Listener();
            finestra.addKeyListener(keyListener);
            finestra.setFocusable(true);

            gameTimer = new Timer(33, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    finestra.repaint();
                }
            });
            gameTimer.start();

        }

    }

    public void stopGame() {
        this.gameTimer.stop();
        this.finestra.dispose();
    }

    public void chiudiFinestra() {
        finestra.setVisible(false);
    }

    public void creaFinestra(Field f) {
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        finestra.getContentPane().setBackground(Color.BLACK);
        if (this.type == 'G') {
            finestra.add(new GamePanel(f));
        } else if (this.type == 'W') {
            finestra.add(new LoadingPanel(true, this.second));
        } else if (this.type == 'E') {
            finestra.add(new EndingPanel(this.result));
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