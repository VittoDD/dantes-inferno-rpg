package com.dantesinferno.rpg.utils;

import com.dantesinferno.rpg.model.GameState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    /**
     * Loads the game state for the JSON save file.
     *
     * @return The loaded GameState, or null if the file doesn't exist or an error occurs
     */
    public static GameState loadGame(){
        Path path = Path.of(SAVE_FILE_PATH);

        if(!Files.exists(path)){
            System.out.println("[SaveManager] No save file found");
            return null;
        }

        try (FileReader reader = new FileReader(SAVE_FILE_PATH)){
            GameState state = gson.fromJson(reader, GameState.class);
            System.out.println("[SaveManager] Game successfully loaded!");
            return state;
        } catch (IOException e){
            System.out.println("[SaveManager] Error during game load " + e.getMessage());
            return null;
        }
    }
}
