package com.dantesinferno.rpg.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 * Main JavaFX application entry point for the Dante's Inferno RPG.
 *
 * @author Vittoria Di Domizio
 * @version 1.1
 */

public class MainApp extends Application{

    private Label levelTitleLabel;
    private Label levelDescLabel;
    private Label quoteLabel;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Dante's Inferno RPG");

        //root layout setup with vertical spacing and dark theme
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #1a1a1a;");

        // UI Labels initialization with custom styling
        levelTitleLabel = new Label("Livello 1: Limbo");
        levelTitleLabel.setStyle("-fx-text-fill: #ff6b6b; -fx-font-size: 24px; -fx-font-weight: bold;");

        levelDescLabel = new Label("Descrizione del livello...");
        levelDescLabel.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 14px; -fx-wrap-text: true;");
        levelDescLabel.setMaxWidth(400);

        quoteLabel = new Label("Citazione di Virgilio...");
        quoteLabel.setStyle("-fx-text-fill: #ffd166; -fx-font-size: 13px; -fx-font-style: italic; -fx-wrap-text: true;");
        quoteLabel.setMaxWidth(400);

        statusLabel = new Label("Cerca l'oggetto nascosto!");
        statusLabel.setStyle("-fx-text-fill: #4ecdc4; -fx-font-size: 14px;");

        root.getChildren().addAll(levelTitleLabel, levelDescLabel, quoteLabel, statusLabel);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
