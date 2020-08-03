package erik.wiesi.view.mainMenu;

import erik.wiesi.model.*;
import erik.wiesi.view.mainMenu.subScenes.*;
import erik.wiesi.view.ViewManager;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MenuScene {

    private AnchorPane mainPane;
    private final String BACKGROUND = "/Background/338101.png";
    private List<ShrineSurvivalButton> buttonList = new ArrayList<>();

    private ShrineSurvivalSubScene subScene;
    private InfoPanel infoPanel;

    private final String CHARSHEET = "/SpriteSheets/roguelikeChar_transparent.png";
    private final String TILESHEET = "/SpriteSheets/roguelikeSheet_transparent.png";

    public MenuScene(AnchorPane mainPane) {
        this.mainPane = mainPane;

        createButtons();
        createBackground();
        buttonActions();

    }

    private void createButtons() {

        double buttonStartX = mainPane.getWidth() / 4;

        buttonList.add(new ShrineSurvivalButton("how to play", "howToPlayButton"));
        buttonList.add(new ShrineSurvivalButton("play solo", "playSoloButton"));
        buttonList.add(new ShrineSurvivalButton("multiplayer", "playmultiplayerButton"));
        buttonList.add(new ShrineSurvivalButton("scores", "scoresButton"));
        buttonList.add(new ShrineSurvivalButton("credits", "creditsButton"));
        buttonList.add(new ShrineSurvivalButton("settings", "settingsButton"));
        buttonList.add(new ShrineSurvivalButton("exit", "exitButton"));

        int totalSize = buttonList.size() * 70;
        AtomicReference<Double> buttonStartY = new AtomicReference<>((mainPane.getHeight() / 2) - (totalSize / 2));
        buttonList.forEach(b -> {
            mainPane.getChildren().add(b);
            b.setLayoutX(buttonStartX);
            b.setLayoutY(buttonStartY.get());
            buttonStartY.updateAndGet(v -> v + 70);
        });

    }

    private void createBackground() {
        Background background = new Background(new BackgroundImage(
                new Image(getClass().getResource(BACKGROUND).toString(), ViewManager.getWIDTH(), ViewManager.getHEIGHT(), false, false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                null
        ));
        mainPane.setBackground(background);
    }

    private void buttonActions() {
        buttonList.forEach(b -> b.setOnAction(actionEvent -> {
            if (b.getButtonName().contains("howToPlay") || b.getButtonName().contains("playSolo") || b.getButtonName().contains("playmultiplayer") || b.getButtonName().contains("scores") || b.getButtonName().contains("credits") || b.getButtonName().contains("settings")) {
                createSubScene(b.getButtonName());
            } else if (b.getButtonName().contains("exit")) {
                Stage stage = (Stage) b.getScene().getWindow();
                stage.close();
            } else {
                System.out.println("Nothing Happenend");
            }
        }));

    }

    private void createSubScene(String buttonName) {
        mainPane.getChildren().remove(subScene);
        switch (buttonName) {
            case "howToPlayButton":
                subScene = new HowToPlaySubScene();
                infoPanel = new InfoPanel("How to play");
                break;
            case "playSoloButton":
                subScene = new PlaySoloSubScene();
                infoPanel = new InfoPanel("Play Solo");
                break;
            case "playmultiplayerButton":
                subScene = new PlayMultiplayerSubScene();
                infoPanel = new InfoPanel("Multiplayer not supported yet");
                break;
            case "scoresButton":
                subScene = new ScoresSubScene();
                infoPanel = new InfoPanel("Scores");
                break;
            case "creditsButton":
                subScene = new CreditsSubScene();
                infoPanel = new InfoPanel("Credits");
                break;
            case "settingsButton":
                subScene = new SettingsSubScene();
                infoPanel = new InfoPanel("Settings");
                break;
            default:
                System.out.println("No SubScene found");
        }
        subScene.setLayoutX(mainPane.getWidth() / 2);
        subScene.setLayoutY((mainPane.getHeight() / 2) - (subScene.getHeight() / 2));
        mainPane.getChildren().add(subScene);
        infoPanel.setLayoutX(40);
        infoPanel.setLayoutY(0);
        subScene.getPane().getChildren().add(infoPanel);
    }
}