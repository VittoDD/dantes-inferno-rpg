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
 * @version 4.0
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
     * Creates the start screen layout
     */
    private Pane createStartRoot(){
        //Use of AnchorPane to position elements freely and with precision
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600,400);

        /**
         * Upload background scene -> Hell's Door
         */

        try {
            Image bgImage =
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/start-scene.jpg")));
            BackgroundImage backgroundImage = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false)
            );
            root.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
                System.out.println("Warning: could not load start screen image. Falling back to color.");
                root.setStyle("-fx-background-color: #121212;");
        }

        /**
         * Set the title in the Start Scene
         */
        Label titleLabel = new Label("DANTE'S INFERNO");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 26px; -fx-font-weight: bold; " +
                "-fx-font-style: italic; -fx-effect: dropshadow(gaussian, black, 8, 0.9, 0, 2)");
        titleLabel.setLayoutX(165);
        titleLabel.setLayoutY(10);

        /**
         * BUTTONS MANAGEMENT -> Start and Resume
         */

        //Verify if a previous save exists
        boolean hasSaveFile = java.nio.file.Files.exists(java.nio.file.Path.of("save.json"));

        //Create the start button
        Button startButton = new Button("Start journey");
        startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75); -fx-text-fill: white; -fx-font-size: 11px; " +
                "-fx-font-weight: bold; -fx-padding: 6 14; -fx-border-color: rgba(255, 209, 102, 0.6);" +
                "-fx-border-width: 1px; -fx-background-radius: 4px; -fx-border-radius: 4px; -fx-cursor: hand; " +
                "-fx-effect: dropshadow(gaussian, black, 5, 0.7, 0, 2);");


        //Create the Resume Button
        Button resumeButton = new Button("Resume journey");
        resumeButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75); -fx-text-fill: white; -fx-font-size: 11px;" +
                "-fx-font-weight: bold; -fx-padding: 6 14; -fx-border-color: rgba(255, 209, 102, 0.6);"+
                "-fx-border-width: 1px; -fx-background-radius: 4px; -fx-border-radius: 4px; -fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, black, 5, 0.7, 0, 2);");

        if(hasSaveFile){
            //if a previous save already exsists, set the two buttons in the first scene
            resumeButton.setLayoutX(230);
            resumeButton.setLayoutY(250);

            startButton.setLayoutX(230);
            startButton.setLayoutY(210);

            root.getChildren().add(resumeButton);
        } else {
            //if a previous save doesn't exist, show only the start button
            startButton.setLayoutX(230);
            startButton.setLayoutY(210);
        }

        //Action for "Start Journey" -> new game: delete any old save files
        startButton.setOnAction(e -> {
            GameManager gm = new GameManager();
            gm.resetGame();
            primaryStageRef.getScene().setRoot(createGameRoot());
        });

        //Action for "Resume Journey" -> start again from the existing file "save.json"
        resumeButton.setOnAction(e -> {
            primaryStageRef.getScene().setRoot(createGameRoot());
        });

        /**
         * Insertion of Virgilio's Avatar in the StartScene
         */
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

        /**
         * Insertion of a box for Virgilio's speech in the Start Scene
         */
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

        /**
         * Create a button in the Start Scene to jump directly to the End Scene

        * Button goEndButton = new Button("GoEnd");
        * goEndButton.setStyle("-fx-background-color: #e63946; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-font-weight: bold; -fx-padding: 10 20; -fx-cursor: hand;");

        * Action to jump to the end of the game
        * goEndButton.setOnAction(e -> {
        *    primaryStageRef.getScene().setRoot(createEndRoot());
        }); */

        root.getChildren().addAll(titleLabel, startButton, virgilioAvatar, speechBubble, danteAvatar
                //,goEndButton
                );

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
                    new BackgroundSize(100, 100, true, true, true, false)
            );
            root.setBackground(new Background(backgroundImage));
        } catch  (Exception e){
            System.out.println("Warning: Could not load paradise background image. Falling back to color.");
            root.setStyle("-fx-background-color: #1e1e2f");
        }

        /**
         * Create and set the title in the End Scene
         */
        Label titleLabel = new Label("JOURNEY COMPLETED");
        titleLabel.setStyle(
                "-fx-text-fill: ffd700; -fx-font-size: 26px; -fx-font-weight: bold; " +
                        "-fx-font-style: italic; -fx-effect: dropshadow(gaussian, black, 8, 0.9, 0, 2)");
        titleLabel.setLayoutX(170);
        titleLabel.setLayoutY(280);

        /**
         * Create a button to restart the game
         */
        Button restartButton = new Button("Play Again");
        restartButton.setStyle(
                "-fx-background-color: rgba(42, 157, 143, 0.45); -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-font-weight: bold; -fx-padding: 8 20; -fx-cursor: hand; -fx-border-color: ffd166;" +
                "-fx-border-width: 1px; -fx-background-radius: 5px; -fx-border-radius: 5px;" +
                "-fx-effect: dropshadow(gaussian, black, 5, 0.7, 0, 2);");

        restartButton.setLayoutX(250);
        restartButton.setLayoutY(200);

        //Action to restart the game and go back to start screen resecting the game state
        restartButton.setOnAction(e -> {
            GameManager gm = new GameManager();
            gm.resetGame();
            primaryStageRef.getScene().setRoot(createStartRoot());
        });

        /**
         * Virgilio's Avatar in the End Scene
         */
        ImageView virgilioAvatar = new ImageView();
        try{
            Image virgilioImg =
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/virgilio-avatar.png")));
            virgilioAvatar.setImage(virgilioImg);
            virgilioAvatar.setFitHeight(150);
            virgilioAvatar.setFitWidth(150);
            virgilioAvatar.setPreserveRatio(true);
        } catch (Exception e){
            System.out.println("Virgilio's avatar not found");
        }

        virgilioAvatar.setLayoutX(10);
        virgilioAvatar.setLayoutY(230);

        /**
         * Box for Virgilio's speech
         */
        VBox speechBubble = new VBox(3);
        speechBubble.setPadding(new Insets(6, 10, 6, 10));
        speechBubble.setStyle("-fx-background-color: rgba(240, 240, 240, 0.65); -fx-border-color: #ffd166;" +
                "-fx-border-width: 1px; -fx-background-radius: 6px; -fx-border-radius: 6px;");
        Label speechLabel = new Label("\"Non aspettar mio dir più né mio cenno;\n" +
                "libero, dritto, sano è tuo arbitrio,\n" +
                "e fallo fora non fare a suo senno...\"");
        speechLabel.setStyle("-fx-text-fill: #1a1a1a; -fx-font-size: 10px; -fx-font-style: italic;");

        speechBubble.getChildren().add(speechLabel);

        speechBubble.setLayoutX(100);
        speechBubble.setLayoutY(325);

        root.getChildren().addAll(titleLabel, restartButton, virgilioAvatar, speechBubble);

        return root;
    }

    /**
     * Creates the main gameplay layout
     */
    private BorderPane createGameRoot() {
        gameManager = new GameManager();

        /**root layout ->
         * BorderPane with global dark background theme
         */

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #121212;");

        /**
         * TOP: Header Bar -> Level Number, Circle Name and Exit button
         */

        headerTitleLabel = new Label();
        headerTitleLabel.setStyle("-fx-text-fill: #e0e0e0; -fx-font-size: 16px; -fx-font-weight: bold;");

        //creation of the exit button
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: linear-gradient(to bottom, #e63946, #9b111e); " +
                "-fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-padding: 4 12; -fx-cursor: hand; " +
                "-fx-background-radius: 4px; -fx-border-color: rgba(255,209,102,0.3); -fx-border-radius: 4px; " +
                "-fx-border-width: 1px; -fx-effect: dropshadow(gaussian, black, 3, 0.5, 0, 1);"
        );

        //Action for the exit button -> go to the first scene
        exitButton.setOnAction( e -> {
            primaryStageRef.getScene().setRoot(createStartRoot());
        });

        StackPane headerPane = new StackPane();

        HBox exitButtonContainer = new HBox(exitButton);
        exitButtonContainer.setAlignment(Pos.CENTER_LEFT);

        headerPane.getChildren().addAll(exitButtonContainer, headerTitleLabel);
        StackPane.setAlignment(headerTitleLabel, Pos.CENTER);
        StackPane.setAlignment(exitButtonContainer, Pos.CENTER_LEFT);

        headerPane.setPadding(new Insets(12, 15, 12, 15));
        headerPane.setStyle("-fx-background-color: #1a1a1a; -fx-border-color: #333333; -fx-border-width: 0 0 1 0;");
        root.setTop(headerPane);

        /**
         * CENTER: Visual Area -> Circle Background Asset
         */

        centerInteractiveArea = new StackPane();
        centerInteractiveArea.setStyle("-fx-background-color: #181818; -fx-cursor: hand;");

        backgroundImageView = new ImageView();
        backgroundImageView.setFitHeight(260);
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setPreserveRatio(true);

        /**
         * Mouse click event listener for direct environment interaction
         */

        centerInteractiveArea.setOnMouseClicked(event -> {
            double clickX = event.getX();
            double clickY = event.getY();

            //print the click's coordinates
            //System.out.println("DEBUG CLICK -> X = " + clickX + " Y = " + clickY);

            /**
             * verify click coordinates against the backend hitboxes
             */

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

        /**
         * BOTTOM: Narrative card -> Virgilio's quote and Status Feedback
         */

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

     /**
      * Updates UI components by retrieving the current Circle state from the GameManager
      */

    private void updateUI() {
        Circle current = gameManager.getCurrentLevel();
        if (current != null) {
            headerTitleLabel.setText("LEVEL " + gameManager.getCurrentLevelNumber() + ": " + current.getName().toUpperCase());
            quoteLabel.setText("Virgil: \"" + current.getVirgilioQuote() + "\"");
            statusLabel.setText("Observe the environment and click on the mystery object");
        }

        /**
         * dinamically load the background image for the current circle
         */

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
