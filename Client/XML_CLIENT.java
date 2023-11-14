import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XML_CLIENT {

    private String tmpFileName;

    public XML_CLIENT(String name) {
        this.tmpFileName = name;
    }

    public String fieldToXML(Field field) throws TransformerException, ParserConfigurationException {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("field");
            doc.appendChild(rootElement);

            rootElement.appendChild(createPlayerElement(doc, "playerOne", field.getPlayerOne()));
            rootElement.appendChild(createPlayerElement(doc, "playerTwo", field.getPlayerTwo()));
            rootElement.appendChild(createBallElement(doc, "ball", field.getBall()));

            Element powerUpsElement = doc.createElement("powerUps");
            rootElement.appendChild(powerUpsElement);

            for (PowerUp powerUp : field.getListPowerUp()) {
                powerUpsElement.appendChild(createPowerUpElement(doc, "powerUp", powerUp));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Element createPlayerElement(Document doc, String elementName, Player player) {
        Element playerElement = doc.createElement(elementName);

        Element paddleElement = doc.createElement("paddle");
        paddleElement.appendChild(createPaddleElement(doc, "paddle", player.getPaddle()));
        playerElement.appendChild(paddleElement);

        Element scoreElement = doc.createElement("score");
        scoreElement.appendChild(doc.createTextNode(String.valueOf(player.getScore())));
        playerElement.appendChild(scoreElement);

        Element setsElement = doc.createElement("sets");
        setsElement.appendChild(doc.createTextNode(String.valueOf(player.getSets())));
        playerElement.appendChild(setsElement);

        Element currentPowerUpElement = doc.createElement("currentPowerUp");
        // Aggiungi i sottoelementi di PowerUp a currentPowerUpElement
        playerElement.appendChild(currentPowerUpElement);

        return playerElement;
    }

    public Element createPaddleElement(Document doc, String elementName, Paddle paddle) {
        Element paddleElement = doc.createElement(elementName);

        Element xElement = doc.createElement("x");
        xElement.appendChild(doc.createTextNode(String.valueOf(paddle.getX())));
        paddleElement.appendChild(xElement);

        Element yElement = doc.createElement("y");
        yElement.appendChild(doc.createTextNode(String.valueOf(paddle.getY())));
        paddleElement.appendChild(yElement);

        Element widthElement = doc.createElement("width");
        widthElement.appendChild(doc.createTextNode(String.valueOf(paddle.getWidth())));
        paddleElement.appendChild(widthElement);

        Element heightElement = doc.createElement("height");
        heightElement.appendChild(doc.createTextNode(String.valueOf(paddle.getHeight())));
        paddleElement.appendChild(heightElement);

        return paddleElement;
    }

    public Element createBallElement(Document doc, String elementName, Ball ball) {

        Element ballElement = doc.createElement(elementName);
    
        Element xElement = doc.createElement("x");
        xElement.appendChild(doc.createTextNode(String.valueOf(ball.getX())));
        ballElement.appendChild(xElement);
    
        Element yElement = doc.createElement("y");
        yElement.appendChild(doc.createTextNode(String.valueOf(ball.getY())));
        ballElement.appendChild(yElement);
    
        Element radiusElement = doc.createElement("radius");
        radiusElement.appendChild(doc.createTextNode(String.valueOf(ball.getRadius())));
        ballElement.appendChild(radiusElement);
    
        Element directionXElement = doc.createElement("directionX");
        directionXElement.appendChild(doc.createTextNode(String.valueOf(ball.getDirectionX())));
        ballElement.appendChild(directionXElement);
    
        Element directionYElement = doc.createElement("directionY");
        directionYElement.appendChild(doc.createTextNode(String.valueOf(ball.getDirectionY())));
        ballElement.appendChild(directionYElement);
    
        Element angleElement = doc.createElement("angle");
        angleElement.appendChild(doc.createTextNode(String.valueOf(ball.getAngle())));
        ballElement.appendChild(angleElement);
    
        Element lastTouchElement = doc.createElement("lastTouch");
        lastTouchElement.appendChild(doc.createTextNode(String.valueOf(ball.getLastTouch())));
        ballElement.appendChild(lastTouchElement);
    
        return ballElement;
    }

    public Element createPowerUpElement(Document doc, String elementName, PowerUp powerUp) {
        Element powerUpElement = doc.createElement(elementName);
    
        Element xElement = doc.createElement("x");
        xElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getX())));
        powerUpElement.appendChild(xElement);
    
        Element yElement = doc.createElement("y");
        yElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getY())));
        powerUpElement.appendChild(yElement);
    
        Element widthElement = doc.createElement("width");
        widthElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getWidth())));
        powerUpElement.appendChild(widthElement);
    
        Element typeElement = doc.createElement("type");
        typeElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getType())));
        powerUpElement.appendChild(typeElement);
    
        Element isActivateElement = doc.createElement("isActivate");
        isActivateElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getIsActivate())));
        powerUpElement.appendChild(isActivateElement);
    
        Element imgElement = doc.createElement("img");
        imgElement.appendChild(doc.createTextNode("DA IMPLEMENTARE"));
        powerUpElement.appendChild(imgElement);
    
        Element isValidElement = doc.createElement("isValid");
        isValidElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getIsValid())));
        powerUpElement.appendChild(isValidElement);
    
        return powerUpElement;
    }
}
