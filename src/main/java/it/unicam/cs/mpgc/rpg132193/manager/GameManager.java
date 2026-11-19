package it.unicam.cs.mpgc.rpg132193.manager;

import it.unicam.cs.mpgc.rpg132193.model.Circle;
import it.unicam.cs.mpgc.rpg132193.model.GameState;
import it.unicam.cs.mpgc.rpg132193.utils.LevelLoader;
import it.unicam.cs.mpgc.rpg132193.utils.SaveManager;

import java.util.List;

/**
 * Manages the game progression, current level state, and win conditions
 * for the Dante's Inferno RPG.
 *
 * @author Vittoria Di Domizio
 * @version 1.4
 */

public class GameManager {
    private final List<Circle> levels;
    private int currentLevelIndex;

    /**
     * Constructs a new GameManager and initializes the 9 levels of Hell
     * checking for a previously saved game state.
     */

    public GameManager() {
        this.levels = LevelLoader.loadLevels();

        if(levels.isEmpty()){
            System.err.println("[GameManager] ALERT: No levels uploaded from the XML file!");
            this.currentLevelIndex = 0;
            return;
        }

        //try to load the saved game state
        GameState savedState = SaveManager.loadGame();
        if(savedState != null){
            //check if the saved index is within bounds
            int savedIndex = savedState.getCurrentLevelIndex();
            if(savedIndex>=0 && savedIndex<=levels.size()){
                this.currentLevelIndex = savedIndex;
                System.out.println("[GameManager] Resumed game from level index: " + currentLevelIndex);
            } else {
                this.currentLevelIndex = 0;
                System.out.println("[GameManager] Saved level index out of bounds. Starting from beginning.");
            }
        } else {
            //DEFAULT -> set the first level (Circle 1)
            this.currentLevelIndex = 0;
            System.out.println("[GameManager] Starting a new game.");

        }
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
     * Advances to the next level if available and automatically saves the game state.
     *
     * @return true if advanced successfully, false if the game is already completed.
     */
    public boolean nextLevel() {
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
            //automatic save of the new game state
            SaveManager.saveGame(new GameState(currentLevelIndex));
            return true;
        } else {
            currentLevelIndex = levels.size(); // Marks game as completed
            //Save the actual state, even if the game is completed
            SaveManager.saveGame(new GameState(currentLevelIndex));
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

    /**
     * Resets the game progression back to the first level and deletes the save file.
     */

    public void resetGame(){
        this.currentLevelIndex = 0;
        SaveManager.deleteSave();
    }
}
