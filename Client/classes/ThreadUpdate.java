import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class ThreadUpdate implements Runnable{
    private Field field;

    /**
     * Costruttore di default
     * @param f campo da gioco che subisce gli aggiornamenti
     */
    public ThreadUpdate(Field f){
        this.field = f;
    }

    /*
     * Operazione che esegue il thread
     * Socket in ascolto per inviare e ricevere gli aggiornamenti
     */
    @Override
    public void run() {
        while (true) {
            try { 
                // Codice per la connessione e l'aggiornamento del campo
                TcpClient tcpService = new TcpClient(Main.SERVER_IP, Main.PORT);
                XML xmlService = new XML("tmp");
                String newXmlField = tcpService.updateField(xmlService.fieldToXML(field));
                //System.out.println(newXmlField);
                Field newField = xmlService.fromXML(newXmlField);
                field.updateField(newField);
                //System.out.println("x: "+field.getBall().getX()+" y: "+ field.getBall().getX());
                // Aggiungi un ritardo per evitare l'overhead eccessivo
                Thread.sleep(50);
            } catch (InterruptedException | IOException | TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }
}
