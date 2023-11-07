import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI 
{
    private static int WIDTH = 1500;
    private static int HEIGHT = 750;

    private JFrame finestra;
    private Listener keyListener;
    private Timer gameTimer;

    public GUI() throws IOException 
    {
        finestra = new JFrame("Pong");
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
    }

    public void creaFinestra(Field f) 
    {
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        finestra.getContentPane().setBackground(Color.BLACK);
        finestra.add(new MyPanel(f));
        finestra.pack();
        finestra.setVisible(true);
    }

    public Listener getListener(){
        return this.keyListener;
    }

    public JFrame getFrame(){
        return this.finestra;
    }
}