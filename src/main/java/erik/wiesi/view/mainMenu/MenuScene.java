package erik.wiesi.view.mainMenu;

import erik.wiesi.model.ShrineSurvivalButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class MenuScene {

    private AnchorPane mainPane;
    private final String BACKGROUND = "/Background/338101.png";

    public MenuScene(AnchorPane mainPane) {
        this.mainPane = mainPane;

        createButtons();
        createBackground();
    }

    private void createButtons() {

        double buttonStartX = mainPane.getWidth() / 4;

        Map<Integer, ShrineSurvivalButton> buttonMap = new HashMap<>();

        buttonMap.put(1, new ShrineSurvivalButton("how to play", "howToPlayButton"));
        buttonMap.put(2, new ShrineSurvivalButton("play solo", "playSoloButton"));
        buttonMap.put(3, new ShrineSurvivalButton("multiplayer", "multiplayerButton"));
        buttonMap.put(4, new ShrineSurvivalButton("scores", "scoresButton"));
        buttonMap.put(5, new ShrineSurvivalButton("credits", "creditsButton"));
        buttonMap.put(6, new ShrineSurvivalButton("settings", "settingsButton"));
        buttonMap.put(7, new ShrineSurvivalButton("exit", "exitButton"));

        int totalSize = buttonMap.size() * 70;
        AtomicReference<Double> buttonStartY = new AtomicReference<>((mainPane.getHeight() / 2) - (totalSize / 2));
        buttonMap.forEach((i, b) -> {
            mainPane.getChildren().add(b);
            b.setLayoutX(buttonStartX);
            b.setLayoutY(buttonStartY.get());
            buttonStartY.updateAndGet(v -> v + 70);
        });

    }

    private void createBackground() {
        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource(BACKGROUND).toString(), 1920, 1080, false, false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                null
        );
        mainPane.setBackground(new Background(background));
    }
}
