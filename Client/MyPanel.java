import java.awt.*;
import javax.swing.*;

class MyPanel extends JPanel {

    private Field field;

    public MyPanel(Field field) {
        this.field = field;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(1500,750);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //sfondo
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //campo
        field.drawField(g);
    }  
}
