package com.dantesinferno.rpg.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import com.dantesinferno.rpg.manager.GameManager;
import com.dantesinferno.rpg.model.Circle;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Main JavaFX application entry point for the Dante's Inferno RPG.
 *
 * @author Vittoria Di Domizio
 * @version 3.0
 */

public class MainApp extends Application{

    private Label headerTitleLabel;
    private Label quoteLabel;
    private Label statusLabel;
    private GameManager gameManager;
    private StackPane centerInteractiveArea;
    private ImageView backgroundImageView;

    @Override
    public void start(Stage primaryStage) {

        //Initialize backend logic
        gameManager = new GameManager();

        primaryStage.setTitle("Dante's Inferno RPG"); }

    /**
     * Creates the main gameplay layout
     */
    private BorderPane createGameRoot() {
        //root layout -> BorderPane with global dark background theme
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #121212;");

        //TOP: Header Bar -> Level Number and Circle Name
        headerTitleLabel = new Label();
        headerTitleLabel.setStyle("-fx-text-fill: #e0e0e0; -fx-font-size: 16px; -fx-font-weight: bold;");

        StackPane headerPane = new StackPane(headerTitleLabel);
        headerPane.setPadding(new Insets(15));
        headerPane.setStyle("-fx-background-color: #1a1a1a; -fx-border-color: #333333; -fx-border-width: 0 0 1 0;");
        root.setTop(headerPane);

        //CENTER: Visual Area -> Circle Background Asset
        centerInteractiveArea = new StackPane();
        centerInteractiveArea.setStyle("-fx-background-color: #181818;");

        backgroundImageView = new ImageView();
        backgroundImageView.setFitHeight(260);
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setPreserveRatio(true);

        //Mouse click event listener for direct environment interaction
        centerInteractiveArea.setOnMouseClicked(event -> {
            double clickX = event.getX();
            double clickY = event.getY();

            //print the click's coordinates
            System.out.println("DEBUG CLICK -> X = " + clickX + " Y = " + clickY);

            //verify click coordinates against the backend hitboxes
            boolean hit = gameManager.checkClickCoordinates(clickX, clickY);
            if (hit) {
                if (gameManager.getCurrentLevelNumber() < 9) {
                    //go to the next level
                    gameManager.nextLevel();
                    updateUI();
                    statusLabel.setText("Object found! Descending to the next Circle...");
                } else {
                    statusLabel.setText("You have successfully completed Dante's Inferno! Congrats!");
                }
            } else {
                //the player must try again
                statusLabel.setText("Nothing here... Search deeper in the shadows...");
            }
        });

        centerInteractiveArea.getChildren().add(backgroundImageView);
        root.setCenter(centerInteractiveArea);

        //BOTTOM: Narrative card -> Virgilio's quote and Status Feedback
        VBox bottomCard = new VBox(8);
        bottomCard.setPadding(new Insets(15));
        bottomCard.setStyle("-fx-background-color: #1a1a1a; -fx-border-color: #333333; -fx-border-width: 1 0 0 0;");
        bottomCard.setAlignment(Pos.CENTER_LEFT);

        quoteLabel = new Label();
        quoteLabel.setStyle("-fx-text-fill: #ffd166; -fx-font-size: 13px; -fx-font-style: italic; -fx-wrap-text: true;");

        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: #4ecdc4; -fx-font-size: 12px;");

        bottomCard.getChildren().addAll(quoteLabel, statusLabel);
        root.setBottom(bottomCard);

        return root;
    }
        //Initialize dynamic UI components based on the starting level (Limbo)
        /*updateUI();

        //Scene and window configuration
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);

        //Lock window size to 600x400
        primaryStage.setResizable(false);
        primaryStage.show();*/


     //Updates UI components by retrieving the current Circle state from the GameManager

    private void updateUI() {
        Circle current = gameManager.getCurrentLevel();
        if (current != null) {
            headerTitleLabel.setText("LEVEL " + gameManager.getCurrentLevelNumber() + ": " + current.getName().toUpperCase());
            quoteLabel.setText("Virgil: \"" + current.getVirgilioQuote() + "\"");
            statusLabel.setText("Observe the environment and click on the mystery object");
        }

        //dinamically load the background image for the current circle
        try{
            assert current != null;
            String imagePath = current.getImagePath();
            Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            backgroundImageView.setImage(bgImage);
        } catch(Exception e) {
            System.out.println("Failed to load background image for " + current.getName());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
