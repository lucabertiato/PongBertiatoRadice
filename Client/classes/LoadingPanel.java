import java.awt.*;
import javax.swing.*;

class LoadingPanel extends JPanel {

    /**
     * Costruttore
     */
    public LoadingPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * Get della dimensione della finestra
     */
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    /**
     * Disegno finestra
     */
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