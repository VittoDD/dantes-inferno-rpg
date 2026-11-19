package it.unicam.cs.mpgc.rpg132193.utils;

import it.unicam.cs.mpgc.rpg132193.model.Circle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class responsible for parsing the XML level configuration file and instantiating the circles of Hell.
 *
 * @author Vittoria Di Domizio
 * @version 1.2
 */
public class LevelLoader {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private LevelLoader(){};

    private static final String XML_PATH = "src/main/resources/levels.xml";

    /**
     * Loads and parses levels from the XML configuration file using the DOM parser.
     *
     * @return A list of Circle objects representing the levels of Hell.
     */

    public static List<Circle> loadLevels(){
        List<Circle> circles = new ArrayList<>();
        File file = new File(XML_PATH);

        if(!file.exists()){
            System.out.println("[LevelLoader] File" + XML_PATH + "not found in the package");
            return circles;
        }

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList circleList = doc.getElementsByTagName("circle");

            for(int i = 0; i< circleList.getLength(); i++){
                Node node = circleList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    int number = Integer.parseInt(element.getAttribute("number"));
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    int dangerLevel = Integer.parseInt(element.getElementsByTagName("dangerLevel").item(0).getTextContent());
                    String virgilioQuote = element.getElementsByTagName("virgilioQuote").item(0).getTextContent();
                    String targetObject = element.getElementsByTagName("targetObject").item(0).getTextContent();
                    String imagePath = element.getElementsByTagName("imagePath").item(0).getTextContent();

                    Element boundsElement = (Element) element.getElementsByTagName("bounds").item(0);
                    double minX = Double.parseDouble(boundsElement.getAttribute("minX"));
                    double maxX = Double.parseDouble(boundsElement.getAttribute("maxX"));
                    double minY = Double.parseDouble(boundsElement.getAttribute("minY"));
                    double maxY = Double.parseDouble(boundsElement.getAttribute("maxY"));

                    Circle circle = new Circle(number, name, description, dangerLevel, virgilioQuote, targetObject, imagePath,
                            minX, maxX, minY, maxY);

                    circles.add(circle);

                }

            }

            System.out.println("[LevelLoader] " + circles.size() + " circles uploaded successfully from XML.");

        } catch (Exception e){
            System.err.println("[LevelLoader] Error during the parsing of XML File" + e.getMessage());
            e.printStackTrace();
        }

        return circles;
    }
}
