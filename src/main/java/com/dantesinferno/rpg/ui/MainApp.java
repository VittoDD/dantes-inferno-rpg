package com.dantesinferno.rpg.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import com.dantesinferno.rpg.manager.GameManager;
import com.dantesinferno.rpg.model.Circle;
import javafx.scene.control.Button;

/**
 * Main JavaFX application entry point for the Dante's Inferno RPG.
 *
 * @author Vittoria Di Domizio
 * @version 1.3
 */

public class MainApp extends Application{

    private Label levelTitleLabel;
    private Label levelDescLabel;
    private Label quoteLabel;
    private Label statusLabel;
    private GameManager gameManager;

    @Override
    public void start(Stage primaryStage) {

        //Initialize backend logic
        gameManager = new GameManager();

        primaryStage.setTitle("Dante's Inferno RPG");

        //root layout setup
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #1a1a1a;");

        // UI Labels setup
        levelTitleLabel = new Label("Livello 1: Limbo");
        levelTitleLabel.setStyle("-fx-text-fill: #ff6b6b; -fx-font-size: 24px; -fx-font-weight: bold;");

        levelDescLabel = new Label("Descrizione del livello...");
        levelDescLabel.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 14px; -fx-wrap-text: true;");
        levelDescLabel.setMaxWidth(400);

        quoteLabel = new Label("Citazione di Virgilio...");
        quoteLabel.setStyle("-fx-text-fill: #ffd166; -fx-font-size: 13px; -fx-font-style: italic; -fx-wrap-text: true;");
        quoteLabel.setMaxWidth(400);

        statusLabel = new Label("Clicca sul pulsante per interagire con l'ambiente!");
        statusLabel.setStyle("-fx-text-fill: #4ecdc4; -fx-font-size: 14px;");

        //interactive action button
        Button actionButton = new Button("Trova Oggetto (Avanza Livello)");
        actionButton.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 14px;");

        actionButton.setOnAction(e -> {
            boolean hit = gameManager.checkClickCoordinates(0.0, 0.0);
            if (hit) {
                if (!gameManager.isGameCompleted()) {
                    gameManager.nextLevel();
                    updateUI();
                    if (gameManager.isGameCompleted()) {
                        statusLabel.setText("Hai completato tutti i 9 Cerchi dell'Inferno!");
                        actionButton.setDisable(true);
                    } else {
                        statusLabel.setText("Complimenti! Sei passato al livello successivo.");
                    }
                }
            }
        });

        root.getChildren().addAll(levelTitleLabel, levelDescLabel, quoteLabel, statusLabel);

        //Load initial level data from backend using updateUI
        updateUI();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);

        //Lock window size to 600x400
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Updates UI components with the current Circle state from GameManager.
     */
    private void updateUI() {
        Circle current = gameManager.getCurrentLevel();
        if (current != null) {
            levelTitleLabel.setText("Livello " + gameManager.getCurrentLevelNumber() + ": " + current.getName());
            levelDescLabel.setText("Descrizione: " + current.getDescription());
            quoteLabel.setText("Virgilio dice: \"" + current.getVirgilioQuote() + "\"");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
