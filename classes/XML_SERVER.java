import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XML_SERVER {
    private String tmpFileName;

    public XML_SERVER(String name){
        this.tmpFileName = name;
    }

    public Field fromXML(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));

            Element rootElement = document.getDocumentElement();
            Field field = new Field();

            // Recupera i valori degli elementi principali
            field.setPlayerOne(parsePlayerElement(rootElement, "playerOne"));
            field.setPlayerTwo(parsePlayerElement(rootElement, "playerTwo"));
            field.setBall(parseBallElement(rootElement, "ball"));
            field.setListPowerUp(parsePowerUps(rootElement, "powerUps"));

            return field;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Player parsePlayerElement(Element parentElement, String elementName) {
        Element playerElement = getChildElement(parentElement, elementName);

        if (playerElement != null) {
            Player player = new Player();
            player.setPaddle(parsePaddleElement(playerElement, "paddle"));
            player.setScore(getChildElementValue(playerElement, "score", 0));
            player.setSets(getChildElementValue(playerElement, "sets", 0));
            player.setCurrentPowerUp(parsePowerUpElement(playerElement, "currentPowerUp"));

            return player;
        }

        return null;
    }

    public Paddle parsePaddleElement(Element parentElement, String elementName) {
        Element paddleElement = getChildElement(parentElement, elementName);

        if (paddleElement != null) {
            Paddle paddle = new Paddle();
            paddle.setX(getChildElementValue(paddleElement, "x", 0));
            paddle.setY(getChildElementValue(paddleElement, "y", 0));
            paddle.setWidth(getChildElementValue(paddleElement, "width", 0));
            paddle.setHeight(getChildElementValue(paddleElement, "height", 0));

            return paddle;
        }

        return null;
    }

    public Ball parseBallElement(Element parentElement, String elementName) {
        Element ballElement = getChildElement(parentElement, elementName);

        if (ballElement != null) {
            Ball ball = new Ball();
            ball.setX(getChildElementValue(ballElement, "x", 0.0));
            ball.setY(getChildElementValue(ballElement, "y", 0.0));
            ball.setRadius(getChildElementValue(ballElement, "radius", 0));
            ball.setDirectionX(getChildElementValue(ballElement, "directionX", '\0'));
            ball.setDirectionY(getChildElementValue(ballElement, "directionY", '\0'));
            ball.setAngle(getChildElementValue(ballElement, "angle", 0));
            ball.setLastTouch(getChildElementValue(ballElement, "lastTouch", 0));

            return ball;
        }

        return null;
    }

    public ArrayList<PowerUp> parsePowerUps(Element parentElement, String elementName) {
        Element powerUpsElement = getChildElement(parentElement, elementName);

        if (powerUpsElement != null) {
            NodeList powerUpNodes = powerUpsElement.getElementsByTagName("powerUp");
            ArrayList<PowerUp> powerUps = new ArrayList<>();

            for (int i = 0; i < powerUpNodes.getLength(); i++) {
                Element powerUpElement = (Element) powerUpNodes.item(i);
                PowerUp powerUp = parsePowerUpElement(powerUpElement, "powerUp");
                if (powerUp != null) {
                    powerUps.add(powerUp);
                }
            }

            return powerUps;
        }

        return null;
    }

    public PowerUp parsePowerUpElement(Element parentElement, String elementName) {
        Element powerUpElement = getChildElement(parentElement, elementName);

        if (powerUpElement != null) {
            PowerUp powerUp = new PowerUp();
            powerUp.setX(getChildElementValue(powerUpElement, "x", 0));
            powerUp.setY(getChildElementValue(powerUpElement, "y", 0));
            powerUp.setWidth(getChildElementValue(powerUpElement, "width", 0));
            powerUp.setType(getChildElementValue(powerUpElement, "type", '\0'));
            powerUp.setIsActivate(getChildElementValue(powerUpElement, "isActivate", false));
            powerUp.setImg(getChildElementValue(powerUpElement, "img", ""));
            powerUp.setIsValid(getChildElementValue(powerUpElement, "isValid", false));

            return powerUp;
        }

        return null;
    }

    public Element getChildElement(Element parentElement, String childElementName) {
        NodeList nodeList = parentElement.getElementsByTagName(childElementName);

        if (nodeList.getLength() > 0) {
            return (Element) nodeList.item(0);
        }

        return null;
    }

    public <T> T getChildElementValue(Element parentElement, String childElementName, T defaultValue) {
        Element childElement = getChildElement(parentElement, childElementName);

        if (childElement != null) {
            String value = childElement.getTextContent();
            return convertStringToType(value, defaultValue);
        }

        return defaultValue;
    }

    public <T> T convertStringToType(String value, T defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }

        Class<?> type = defaultValue.getClass();

        if (type == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (type == Double.class) {
            return (T) Double.valueOf(value);
        } else if (type == Character.class) {
            return (T) Character.valueOf(value.charAt(0));
        } else if (type == Boolean.class) {
            return (T) Boolean.valueOf(value);
        } else {
            return (T) value;
        }
    }
}
