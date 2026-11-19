package it.unicam.cs.mpgc.rpg132193.manager;

import it.unicam.cs.mpgc.rpg132193.utils.SaveManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the GameManager class, verifying level progression
 * and 2D coordinate click hit-detection.
 *
 * @author Vittoria Di Domizio
 * @version 1.2
 */

public class GameManagerTest {
    private GameManager gameManager;

    @BeforeEach
    public void setUp() {
        // Cleans up any existing save file beforehand to ensure that the test always starts from
        //a clean environment (Level 1 - Limbo)
        SaveManager.deleteSave();

        //Initialize a fresh GameManager instance before each test
        gameManager = new GameManager();
    }

    @Test
    public void testInitialLevelIsLimbo() {
        assertEquals(1, gameManager.getCurrentLevelNumber(), "Game should start at Level 1");
        assertNotNull(gameManager.getCurrentLevel(), "Current level should not be null");
        assertEquals("Limbo", gameManager.getCurrentLevel().getName(), "First level must be Limbo");
    }

    @Test
    public void testClickInsideTargetRadius() {
        // GameManager automatically loads LevelData starting at index 0 (Limbo).
        // Limbo's hitbox boundaries in LevelData are: X: [240.0, 290.0], Y: [170.0, 220.0].
        // A click at (260.0, 195.0) falls directly inside these boundaries.
        boolean hit = gameManager.checkClickCoordinates(260.0, 195.0);

        assertTrue(hit, "Click within the target hitbox boundaries should be successful");
    }

    @Test
    public void testClickOutsideTargetRadius() {
        // A click at (60.0, 60.0) has a distance > 50.0
        boolean hit = gameManager.checkClickCoordinates(60.0, 60.0);
        assertFalse(hit, "Click outside the target radius should fail");
    }

    @Test
    public void testLevelProgression() {
        assertEquals(1, gameManager.getCurrentLevelNumber());

        // Advance through levels
        boolean advanced = gameManager.nextLevel();
        assertTrue(advanced, "Should successfully advance to level 2");
        assertEquals(2, gameManager.getCurrentLevelNumber(), "Current level should now be 2");
        assertEquals("Lust", gameManager.getCurrentLevel().getName());
    }

    @Test
    public void testCompleteAllLevels() {
        // There are 9 levels. Advancing 8 times should reach level 9.
        for (int i = 0; i < 8; i++) {
            gameManager.nextLevel();
        }
        assertEquals(9, gameManager.getCurrentLevelNumber(), "Should be at the final level (9)");
        assertFalse(gameManager.isGameCompleted(), "Game should not be completed yet before passing level 9");

        // Try to advance past the last level
        boolean advancedBeyondLast = gameManager.nextLevel();
        assertFalse(advancedBeyondLast, "Should not be able to advance past level 9");
        assertTrue(gameManager.isGameCompleted(), "Game should be marked as completed");
        assertNull(gameManager.getCurrentLevel(), "Current level should be null after game completion");
    }
}
