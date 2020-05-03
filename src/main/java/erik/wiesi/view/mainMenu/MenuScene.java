package erik.wiesi.view.mainMenu;

import erik.wiesi.model.ShrineSurvivalButton;
import erik.wiesi.model.ShrineSurvivalSubScene;
import erik.wiesi.model.subScenes.*;
import erik.wiesi.view.ViewManager;
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

    private ShrineSurvivalSubScene subScene;
    private PanelInfo panelInfo;

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
                new Image(getClass().getResource(BACKGROUND).toString(), ViewManager.getWIDTH(), ViewManager.getHEIGHT(), false, false),
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
                if (b.getButtonName().contains("howToPlay") || b.getButtonName().contains("playmultiplayer") || b.getButtonName().contains("scores") || b.getButtonName().contains("credits") || b.getButtonName().contains("settings")) {
                    createSubScene(b.getButtonName());
                } else if (b.getButtonName().contains("exit")) {
                    Stage stage = (Stage) b.getScene().getWindow();
                    stage.close();
                } else if (b.getButtonName().contains("playSolo")) {
                    System.out.println("Play Solo");
                } else {
                    System.out.println("Nothing Happenend");
                }
            }
        }));

    }

    private void createSubScene(String buttonName) {
        mainPane.getChildren().remove(subScene);
        switch (buttonName) {
            case "howToPlayButton":
                subScene = new HowToPlaySubScene();
                panelInfo = new PanelInfo("How to play");
                break;
            case "playmultiplayerButton":
                subScene = new PlayMultiplayerSubScene();
                panelInfo = new PanelInfo("Multiplayer not supported yet");
                break;
            case "scoresButton":
                subScene = new ScoresSubScene();
                panelInfo = new PanelInfo("Scores");
                break;
            case "creditsButton":
                subScene = new CreditsSubScene();
                panelInfo = new PanelInfo("Credits");
                break;
            case "settingsButton":
                subScene = new SettingsSubScene();
                panelInfo = new PanelInfo("Settings");
                break;
            default:
                System.out.println("No SubScene found");
        }
        subScene.setOpacity(0.75);
        subScene.setLayoutX(mainPane.getWidth() / 2);
        subScene.setLayoutY((mainPane.getHeight() / 2) - (subScene.getHeight() / 2));
        mainPane.getChildren().add(subScene);
        panelInfo.setLayoutX(40);
        panelInfo.setLayoutY(0);
        subScene.getPane().getChildren().add(panelInfo);
    }
}