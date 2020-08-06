package erik.wiesi.view.mainMenu.subScenes;

import erik.wiesi.model.ShrineSurvivalSubScene;
import erik.wiesi.view.ViewManager;
import erik.wiesi.view.mainMenu.InfoPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class HowToPlaySubScene extends ShrineSurvivalSubScene {

    public HowToPlaySubScene() {

        List<InfoPanel> text = new ArrayList<>();
        text.add(new InfoPanel("Start Game by clicking SoloGame"));
        text.add(new InfoPanel("Choose your Character"));
        text.add(new InfoPanel("press start"));
        text.add(new InfoPanel("Move with Arrowkeys"));
        text.add(new InfoPanel("Attack with W, A, S, D"));

        int totalSize = text.size() * 70;
        AtomicReference<Double> panelStartY = new AtomicReference<>((getPane().getHeight() / 2) - (totalSize / 2));
        text.forEach(panel -> {
            double panelStartX = (ViewManager.getWIDTH() / 32) - (panel.getWidth() / 2);
            getPane().getChildren().add(panel);
            panel.setLayoutX(panelStartX);
            panel.setLayoutY(panelStartY.get());
            panelStartY.updateAndGet(v -> v + 70);
        });
    }
}