package com.dantesinferno.rpg.manager;

import com.dantesinferno.rpg.model.Circle;
import com.dantesinferno.rpg.model.LevelData;
import java.util.List;

/**
 * Manages the game progression, current level state, and win conditions
 * for the Dante's Inferno RPG.
 *
 * @author Vittoria Di Domizio
 * @version 1.0
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

    // TODO: Methods for game logic (getCurrentLevel, checkTarget, nextLevel, isGameCompleted)
}
