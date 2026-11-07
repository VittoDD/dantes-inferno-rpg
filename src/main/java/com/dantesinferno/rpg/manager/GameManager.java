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
     * Checks if the clicked coordinates (X, Y) fall inside the target hitbox
     * for the current Circle of Hell.
     *
     * @param x Click X coordinate relative to the ImageView
     * @param y Click Y coordinate relative to the ImageView
     * @return true if the click hits the target, false otherwise
     */

    public boolean checkClickCoordinates(double x, double y) {
        Circle currentCircle = getCurrentLevel();
        if (currentCircle!=null){
            return currentCircle.contains(x, y);
        }
        return false;
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
