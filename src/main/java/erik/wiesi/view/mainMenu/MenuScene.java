package erik.wiesi.view.mainMenu;

import erik.wiesi.model.ShrineSurvivalButton;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class MenuScene {

    private AnchorPane mainPane;

    public MenuScene(AnchorPane mainPane) {
        this.mainPane = mainPane;

        createButtons();
    }

    private void createButtons() {

        double buttonStartX = mainPane.getWidth() / 4;

        Map<Integer, ShrineSurvivalButton> buttonMap = new HashMap<>();

        buttonMap.put(1, new ShrineSurvivalButton("how to play", "howToPlayButton"));
        buttonMap.put(2, new ShrineSurvivalButton("play solo", "playSoloButton"));
        buttonMap.put(3, new ShrineSurvivalButton("multiplayer", "multiplayerButton"));
        buttonMap.put(4, new ShrineSurvivalButton("settings", "settingsButton"));
        buttonMap.put(5, new ShrineSurvivalButton("exit", "exitButton"));

        int totalSize = buttonMap.size() * 70;
        AtomicReference<Double> buttonStartY = new AtomicReference<>((mainPane.getHeight() / 2) - (totalSize / 2));
        buttonMap.forEach((i, b) -> {
            mainPane.getChildren().add(b);
            b.setLayoutX(buttonStartX);
            b.setLayoutY(buttonStartY.get());
            buttonStartY.updateAndGet(v -> new Double((double) (v + 70)));
        });

    }

    private void createBackground() {

    }
}
