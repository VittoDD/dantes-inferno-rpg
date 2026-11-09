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
    private Pane createStartRoot(){
        //Use of AnchorPane to position elements freely and with precision
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600,400);

        //Upload background scene -> Hell's Door
        try {
            Image bgImage =
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/start-scene.jpg")));
            BackgroundImage backgroundImage = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    //fit the image to the screen size
                    new BackgroundSize(100, 100, true, true, true, false)
            );
            root.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
                System.out.println("Warning: could not load start screen image. Falling back to color.");
                root.setStyle("-fx-background-color: #121212;");
        }

        //Title moved to the top center
        Label titleLabel = new Label("DANTE'S INFERNO");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 26px; -fx-font-weight: bold; " +
                "-fx-font-style: italic; -fx-effect: dropshadow(gaussian, black, 8, 0.9, 0, 2)");
        titleLabel.setLayoutX(165);
        titleLabel.setLayoutY(10);

        //Action button to begin the game -> moved on the Hell's Door in the background
        Button startButton = new Button("Start journey");
        startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75); -fx-text-fill: white; -fx-font-size: 11px; " +
                "-fx-font-weight: bold; -fx-padding: 6 14; -fx-border-color: rgba(255, 209, 102, 0.6);" +
                "-fx-border-width: 1px; -fx-background-radius: 4px; -fx-border-radius: 4px; -fx-cursor: hand; " +
                "-fx-effect: dropshadow(gaussian, black, 5, 0.7, 0, 2);");
        startButton.setLayoutX(230);
        startButton.setLayoutY(210);

        startButton.setOnAction(e -> {
            primaryStageRef.getScene().setRoot(createGameRoot());
        });

        //Insertion and positioning of Virgilio's Avatar
        ImageView virgilioAvatar = new ImageView();
        try{
            Image virgilioImg =
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/virgilio-avatar.png")));
            virgilioAvatar.setImage(virgilioImg);
            virgilioAvatar.setFitHeight(140);
            virgilioAvatar.setFitWidth(140);
            virgilioAvatar.setPreserveRatio(true);
        } catch (Exception e){
            System.out.println("Virgilio's avatar not found");
        }

        virgilioAvatar.setLayoutX(40);
        virgilioAvatar.setLayoutY(220);

        //box for Virgilio's speech
        VBox speechBubble = new VBox(3);
        speechBubble.setPadding(new Insets(6, 10, 6, 10));
        speechBubble.setStyle("-fx-background-color: rgba(240, 240, 240, 0.65); -fx-border-color: #ffd166;" +
                "-fx-border-width: 1px; -fx-background-radius: 6px; -fx-border-radius: 6px;");
        Label speechLabel = new Label("\"Ond’io per lo tuo me’ penso e discerno\n" +
                "che tu mi segui, e io sarò tua guida,\n" +
                "e trarrotti di qui per loco etterno...\"");
        speechLabel.setStyle("-fx-text-fill: #1a1a1a; -fx-font-size: 10px; -fx-font-style: italic;");

        speechBubble.getChildren().add(speechLabel);

        speechBubble.setLayoutX(120);
        speechBubble.setLayoutY(325);

        /**
         * Insertion and positioning of Dante's avatar
         */

        ImageView danteAvatar = new ImageView();

        try{
            Image danteImg =
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dante-avatar.png")));
            danteAvatar.setImage(danteImg);
            danteAvatar.setFitHeight(200);
            danteAvatar.setFitWidth(200);
            danteAvatar.setPreserveRatio(true);
        } catch(Exception e) {
            System.out.println("Virgilio's avatar not found");
        }

        danteAvatar.setLayoutX(260);
        danteAvatar.setLayoutY(240);

        root.getChildren().addAll(titleLabel, startButton, virgilioAvatar, speechBubble, danteAvatar);

        return root;
    }

    /**
     * Creates the end screen layout using an AnchorPane
     */
    private AnchorPane createEndRoot(){
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 400);

        /**
         * Add the Paradise background scene
         */

        try{
            Image bgImage =
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/end-scene.jpg")));
            BackgroundImage backgroundImage = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(600, 400, false, false, false, true)
            );
            root.setBackground(new Background(backgroundImage));
        } catch  (Exception e){
            System.out.println("Warning: Could not load paradise background image. Falling back to color.");
            root.setStyle("-fx-background-color: #1e1e2f");
        }

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

        root.getChildren().addAll(titleLabel, descLabel, restartButton);

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
