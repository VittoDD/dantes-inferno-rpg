package com.dantesinferno.rpg.utils;

import com.dantesinferno.rpg.model.Circle;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class LevelLoader {
    private static final String XML_PATH = "levels.xml";

    /**
     * Upload levels from the XML file (initial scheleton with DOM)
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

            //TODO: Add parsing logic

            System.out.println("[LevelLoader] XML File read correctly.");

        } catch (Exception e){
            System.err.println("[LevelLoader] Error during the parsing of XML File" + e.getMessage());
            e.printStackTrace();
        }

        return circles;
    }
}
