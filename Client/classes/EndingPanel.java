import java.awt.*;
import javax.swing.*;

class EndingPanel extends JPanel {

    private boolean win;

    public EndingPanel(int result) {
        if (result == 1) {
            this.win = true;
        } else if (result == 2) {
            this.win = false;
        }
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
        String message = "";
        if(this.win){
            message = "Hai vinto!";
        }
        else{
            message = "Hai perso!";
        }
        g.drawString(message, 50, 50);
    }
}
