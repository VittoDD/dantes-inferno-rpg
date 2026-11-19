package it.unicam.cs.mpgc.rpg132193.model;

/**
 * Represents the save state of the Dante's Inferno RPG,
 * storing key progression data like the current level index.
 *
 * @author Vittoria Di Domizio
 * @version 1.0
 */

public class GameState {
    private int currentLevelIndex;

    /**
     * Default constructor required by Gson for deserialization.
     */

    public GameState() {}

    /**
     * Constructs a new game with the specified level index.
     *
     * @param currentLevelIndex The index of the current active level
     */

    public GameState(int currentLevelIndex){
        this.currentLevelIndex = currentLevelIndex;
    }

    /**
     * Gets the current active level index.
     *
     * @return The level index integer.
     */
    public int getCurrentLevelIndex(){
        return currentLevelIndex;
    }

    /**
     * Sets the current active level index.
     *
     * @param currentLevelIndex The new level index to set.
     */
    public void setCurrentLevelIndex(int currentLevelIndex){
        this.currentLevelIndex = currentLevelIndex;
    }
}
