package com.dantesinferno.rpg.utils;

import com.dantesinferno.rpg.model.Circle;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LevelLoaderTest {

    @Test
    public void testLoadLevelsSuccessfully(){
        List<Circle> circles = LevelLoader.loadLevels();

        //Structural verifies
        assertNotNull(circles, "Circles list mustn't be null");
        assertFalse(circles.isEmpty(), "Circles list mustn't be empty");

        //Verifies of the first circle's data
        Circle firstCircle = circles.get(0);
        assertEquals(1, firstCircle.getNumber(), "Circle's number has to be 1");
        assertEquals("Limbo", firstCircle.getName(), "Circle's name has to be Limbo");
        assertEquals(1, firstCircle.getDangerLevel(), "Circle's dangerLevel has to be 1");
        assertEquals("\"Tra ombre di saggi e poeti senza speme, cerca ciò che custodisce la vera sapienza.\"",
                firstCircle.getVirgilioQuote(), "Virgilio's quote is wrong");

        //Verifies spacial bounds
        assertEquals(240.0, firstCircle.getMinX(), 0.001);
        assertEquals(290.0, firstCircle.getMaxX(), 0.001);
    }
}
