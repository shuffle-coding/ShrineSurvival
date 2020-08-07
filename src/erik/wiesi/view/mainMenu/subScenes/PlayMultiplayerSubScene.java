package erik.wiesi.view.mainMenu.subScenes;

import erik.wiesi.model.ShrineSurvivalSubScene;
import erik.wiesi.view.ViewManager;
import erik.wiesi.view.mainMenu.InfoPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PlayMultiplayerSubScene extends ShrineSurvivalSubScene {

    public PlayMultiplayerSubScene() {
        List<InfoPanel> text = new ArrayList<>();
        text.add(new InfoPanel("Multiplayer"));
        text.add(new InfoPanel("not"));
        text.add(new InfoPanel("supported"));
        text.add(new InfoPanel("yet!"));

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
