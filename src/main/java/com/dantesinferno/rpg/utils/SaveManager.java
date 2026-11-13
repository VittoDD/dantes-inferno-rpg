package com.dantesinferno.rpg.utils;

import com.dantesinferno.rpg.model.GameState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Manages saving and loading the game state using Google Gson for JSON serialization.
 *
 * @author Vittoria Di Domizio
 * @version 1.0
 */

public class SaveManager {
    private static final String SAVE_FILE_PATH = "save.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Saves the current game state to a JSON file
     *
     * @param state The GameState to serialize and save
     */
    public static void saveGame(GameState state){
        try (FileWriter writer = new FileWriter(SAVE_FILE_PATH)) {
            gson.toJson(state, writer);
            System.out.println("[SaveManager] Game successfully saved to " + SAVE_FILE_PATH);
        } catch (IOException e){
            System.out.println("[SaveManager] Error during game save: " + e.getMessage());
        }
    }

    //TODO: Add a method to load the game state from the JSON file
}
