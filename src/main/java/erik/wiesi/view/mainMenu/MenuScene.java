package erik.wiesi.view.mainMenu;

import erik.wiesi.model.ShrineSurvivalButton;
import erik.wiesi.model.ShrineSurvivalSubScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MenuScene {

    private AnchorPane mainPane;
    private final String BACKGROUND = "/Background/338101.png";
    private List<ShrineSurvivalButton> buttonMap = new ArrayList<>();

    private ShrineSurvivalSubScene howToPlaySubScene;
    private ShrineSurvivalSubScene coresSubScene;
    private ShrineSurvivalSubScene creditsSubScene;
    private ShrineSurvivalSubScene settingsSubScene;

    public MenuScene(AnchorPane mainPane) {
        this.mainPane = mainPane;

        createButtons();
        createBackground();
        buttonActions();
    }

    private void createButtons() {

        double buttonStartX = mainPane.getWidth() / 4;

        buttonMap.add(new ShrineSurvivalButton("how to play", "howToPlayButton"));
        buttonMap.add(new ShrineSurvivalButton("play solo", "playSoloButton"));
        buttonMap.add(new ShrineSurvivalButton("multiplayer", "playmultiplayerButton"));
        buttonMap.add(new ShrineSurvivalButton("scores", "scoresButton"));
        buttonMap.add(new ShrineSurvivalButton("credits", "creditsButton"));
        buttonMap.add(new ShrineSurvivalButton("settings", "settingsButton"));
        buttonMap.add(new ShrineSurvivalButton("exit", "exitButton"));

        int totalSize = buttonMap.size() * 70;
        AtomicReference<Double> buttonStartY = new AtomicReference<>((mainPane.getHeight() / 2) - (totalSize / 2));
        buttonMap.forEach(b -> {
            mainPane.getChildren().add(b);
            b.setLayoutX(buttonStartX);
            b.setLayoutY(buttonStartY.get());
            buttonStartY.updateAndGet(v -> v + 70);
        });

    }

    private void createBackground() {
        Background background = new Background(new BackgroundImage(
                new Image(getClass().getResource(BACKGROUND).toString(), 1920, 1080, false, false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                null
        ));
        mainPane.setBackground(background);
    }

    private void buttonActions() {
        buttonMap.forEach(b -> b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switch (b.getButtonName()) {
                    case "howToPlayButton":
                        createHowToPlaySubScene();
                        break;
                    case "playSoloButton":

                        break;
                    case "playmultiplayerButton":
                        createPlayMultiplayerSubScene();
                        break;
                    case "scoresButton":
                        createScoresSubScene();
                        break;
                    case "creditsButton":
                        createCreditsSubScene();
                        break;
                    case "settingsButton":
                        createSettingsSubScene();
                        break;
                    case "exitButton":
                        Stage stage = (Stage) b.getScene().getWindow();
                        stage.close();
                        break;
                    default:
                        System.out.println("Nothing Happenend");

                }
            }
        }));

    }

    public void createHowToPlaySubScene() {

        ShrineSurvivalSubScene subScene = new ShrineSurvivalSubScene();
        subScene.setOpacity(0.5);
        subScene.setLayoutX(mainPane.getWidth() / 2);
        subScene.setLayoutY((mainPane.getHeight() / 2) - (subScene.getHeight() / 2));
        PanelInfo panelInfo = new PanelInfo("test");
        panelInfo.setLayoutX(40);
        panelInfo.setLayoutY(60);
        mainPane.getChildren().add(subScene);
        subScene.getPane().getChildren().add(panelInfo);

    }

    public void createScoresSubScene() {

        ShrineSurvivalSubScene subScene = new ShrineSurvivalSubScene();
        subScene.setOpacity(0.5);
        subScene.setLayoutX(mainPane.getWidth() / 2);
        subScene.setLayoutY((mainPane.getHeight() / 2) - (subScene.getHeight() / 2));
        mainPane.getChildren().add(subScene);
    }

    public void createCreditsSubScene() {

        ShrineSurvivalSubScene subScene = new ShrineSurvivalSubScene();
        subScene.setOpacity(0.5);
        subScene.setLayoutX(mainPane.getWidth() / 2);
        subScene.setLayoutY((mainPane.getHeight() / 2) - (subScene.getHeight() / 2));
        mainPane.getChildren().add(subScene);

    }

    public void createSettingsSubScene() {

        ShrineSurvivalSubScene subScene = new ShrineSurvivalSubScene();
        subScene.setOpacity(0.5);
        subScene.setLayoutX(mainPane.getWidth() / 2);
        subScene.setLayoutY((mainPane.getHeight() / 2) - (subScene.getHeight() / 2));
        mainPane.getChildren().add(subScene);

    }

    public void createPlayMultiplayerSubScene() {

        ShrineSurvivalSubScene subScene = new ShrineSurvivalSubScene();
        subScene.setOpacity(0.5);
        subScene.setLayoutX(mainPane.getWidth() / 2);
        subScene.setLayoutY((mainPane.getHeight() / 2) - (subScene.getHeight() / 2));
        mainPane.getChildren().add(subScene);

    }

}
