import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener{
    //ultimo tasto premuto
    private int lastPressedKeyCode = -1;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //aggiorna ultimo tasto premuto
        this.lastPressedKeyCode = keyCode;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == lastPressedKeyCode) {
            //resetta variabile quando il tasto viene lasciato
            this.lastPressedKeyCode = -1;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Questo metodo si attiva quando si digita un tasto (ad esempio, un carattere).
    }

    public int getLastKeyPressed(){
        return this.lastPressedKeyCode;
    }

}
