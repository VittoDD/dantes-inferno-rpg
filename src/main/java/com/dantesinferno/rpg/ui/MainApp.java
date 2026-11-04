package com.dantesinferno.rpg.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main JavaFX application entry point for the Dante's Inferno RPG.
 *
 * @author Vittoria Di Domizio
 * @version 1.0
 */

public class MainApp extends Application{
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dante's Inferno RPG - MVP");

        VBox root = new VBox();
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
