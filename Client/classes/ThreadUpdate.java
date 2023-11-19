import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class ThreadUpdate implements Runnable{
    private Field field;

    public ThreadUpdate(Field f){
        this.field = f;
    }

    @Override
    public void run() {
        while (true) {
            try { 
                // Codice per la connessione e l'aggiornamento del campo
                TcpClient tcpService = new TcpClient("localhost", 667);
                XML xmlService = new XML("tmp");
                String newXmlField = tcpService.updateField(xmlService.fieldToXML(field));
                //System.out.println(newXmlField);
                Field newField = xmlService.fromXML(newXmlField);
                field.updateField(newField);
                // Aggiungi un ritardo per evitare l'overhead eccessivo
                Thread.sleep(50);
            } catch (InterruptedException | IOException | TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }
}
