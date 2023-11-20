import java.awt.*;
import javax.swing.*;

class LoadingPanel extends JPanel {

    private boolean clientsConnected;
    private int countdownTime;

    public LoadingPanel(boolean bool, int s) {
        this.clientsConnected = bool;
        this.countdownTime = s;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // sfondo
        g.setColor(Color.RED);
        Font customFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(customFont);
        g.drawString("Attesa connessione avversario...", 50, 50);
    }
}