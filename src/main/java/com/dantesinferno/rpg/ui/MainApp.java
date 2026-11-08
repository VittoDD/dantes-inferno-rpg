package com.dantesinferno.rpg.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import com.dantesinferno.rpg.manager.GameManager;
import com.dantesinferno.rpg.model.Circle;
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
    private Stage primaryStageRef;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStageRef = primaryStage;
        primaryStage.setTitle("Dante's Inferno RPG");

        //Start with the Start Screen Root
        Scene scene = new Scene(createStartRoot(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
         }

    /**
     * Creates the start screen layout (Base Version)
     */
    private BorderPane createStartRoot(){
        BorderPane root = new BorderPane();

        //Upload background scene
        try {
            Image bgImage =
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/start-scene.jpg")));
            BackgroundImage backgroundImage = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    //fit the image to the screen size
                    new BackgroundSize(600, 400, false, false, false, true)
            );
            root.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
                System.out.println("Warning: could not load start screen image. Falling back to color.");
                root.setStyle("-fx-background-color: #121212;");
        }

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(40));

        Label titleLabel = new Label("DANTE'S INFERNO");
        titleLabel.setStyle("-fx-text-fill: #ffd166; -fx-font-size: 24px; -fx-font-weight: bold;");

        Button startButton = new Button("Start journey");
        startButton.setStyle("-fx-background-color: #e63946; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-font-weight: bold; -fx-padding: 10 20; -fx-cursor: hand;");

        //Action button to begin the game
        startButton.setOnAction(e -> {
            primaryStageRef.getScene().setRoot(createGameRoot());
        });

        contentBox.getChildren().addAll(titleLabel, startButton);
        root.setCenter(contentBox);

        return root;
    }

    /**
     * Creates the end screen layout (base version)
     */
    private BorderPane createEndRoot(){
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #121212;");

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(40));

        Label titleLabel = new Label("JOURNEY COMPLETED");
        titleLabel.setStyle("-fx-text-fill: #ffd166; -fx-font-size: 24px; -fx-font-weight: bold;");

        Label descLabel = new Label("You have successfully traversed all 9 Circles of Hell!");
        descLabel.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 13px; -fx-alignment: center;");

        //Button to restart the game
        Button restartButton = new Button("Restart");
        restartButton.setStyle("-fx-background-color: #e63946; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-font-weight: bold; -fx-padding: 10 20; -fx-cursor: hand;");

        //Action to restart the game and go back to start screen
        restartButton.setOnAction(e -> {
            primaryStageRef.getScene().setRoot(createStartRoot());
        });

        contentBox.getChildren().addAll(titleLabel, descLabel, restartButton);
        root.setCenter(contentBox);

        return root;
    }

    /**
     * Creates the main gameplay layout
     */
    private BorderPane createGameRoot() {
        gameManager = new GameManager();

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
                    //completed all the 9 levels of the game -> switch to End Scene
                    primaryStageRef.getScene().setRoot(createEndRoot());
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

        updateUI();

        return root;
    }

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
