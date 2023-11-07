import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener{
    private int lastPressedKeyCode = -1; // Variabile di stato per memorizzare l'ultima chiave premuta

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        this.lastPressedKeyCode = keyCode; // Aggiorna la variabile di stato con l'ultimo tasto premuto
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == lastPressedKeyCode) {
            this.lastPressedKeyCode = -1; // Resetta la variabile di stato quando il tasto viene rilasciato
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
