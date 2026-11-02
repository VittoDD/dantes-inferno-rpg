package com.dantesinferno.rpg.manager;

import com.dantesinferno.rpg.model.Circle;
import com.dantesinferno.rpg.model.LevelData;
import java.util.List;

/**
 * Manages the game progression, current level state, and win conditions
 * for the Dante's Inferno RPG.
 *
 * @author Vittoria Di Domizio
 * @version 1.2
 */

public class GameManager {
    private final List<Circle> levels;
    private int currentLevelIndex;

    /**
     * Constructs a new GameManager and initializes the 9 levels of Hell.
     */
    public GameManager() {
        this.levels = LevelData.getInfernoCircles();
        this.currentLevelIndex = 0; // Starts at Circle 1 (index 0)
    }

    /**
     * Gets the circle representing the current active level.
     *
     * @return The current Circle, or null if all levels are completed.
     */

    public Circle getCurrentLevel() {
        if (currentLevelIndex < levels.size()) {
            return levels.get(currentLevelIndex);
        }
        return null;
    }

    /**
     * Verifies if a user's mouse click coordinates hit the hidden target object of the current level.
     * Uses Euclidean distance calculation: sqrt((x2-x1)^2 + (y2-y1)^2) <= radius.
     *
     * @param clickX The X coordinate of the mouse click
     * @param clickY The Y coordinate of the mouse click
     * @return true if the click is within the target object's radius, false otherwise
     */
    public boolean checkClickCoordinates(double clickX, double clickY) {
        Circle current = getCurrentLevel();
        if (current == null) {
            return false;
        }

        double deltaX = clickX - current.getTargetX();
        double deltaY = clickY - current.getTargetY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return distance <= current.getTargetRadius();
    }

    /**
     * Advances to the next level if available.
     *
     * @return true if advanced successfully, false if the game is already completed.
     */
    public boolean nextLevel() {
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
            return true;
        } else {
            currentLevelIndex = levels.size(); // Marks game as completed
            return false;
        }
    }

    /**
     * Checks if the user has completed all 9 levels of Hell.
     *
     * @return true if the game is completed, false otherwise.
     */
    public boolean isGameCompleted() {
        return currentLevelIndex >= levels.size();
    }

    /**
     * Gets the current level number (1-based, from 1 to 9).
     *
     * @return The 1-based level number.
     */
    public int getCurrentLevelNumber() {
        return Math.min(currentLevelIndex + 1, levels.size());
    }
}
