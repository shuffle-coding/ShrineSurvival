package erik.wiesi.model.subScenes;

import erik.wiesi.model.ShrineSurvivalButton;
import erik.wiesi.model.ShrineSurvivalSubScene;
import erik.wiesi.model.SpriteTest;
import erik.wiesi.model.SpritesheetTest;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class PlaySoloSubScene extends ShrineSurvivalSubScene{


    private final String BACKGROUND = "/Background/blue_panel.png";
    private List<ShrineSurvivalButton> buttonList = new ArrayList<>();

    public PlaySoloSubScene() {

        SpritesheetTest chars = new SpritesheetTest("/SpriteSheets/roguelikeChar_transparent.png");
        SpriteTest test = new SpriteTest(chars);
        test.addSprite(0,1);
        test.addSprite(3, 1);
        test.addSprite(4, 1);
        test.addSprite(6, 1);
        test.addSprite(20, 1);
        test.addSprite(30, 1);
        test.addSprite(50, 1);
        this.getPane().getChildren().add(test.getCanvas());
        test.setLayout(this.getPane().getWidth() / 4, this.getPane().getHeight() / 2);
        test.setScale(12);

        addButtons();
        addStartButton();
        buttonActions();

    }

    private void addButtons() {

        double buttonStartX = this.getPane().getWidth() / 1.6;

        buttonList.add(new ShrineSurvivalButton("body next", "bodyNextButton"));
        buttonList.add(new ShrineSurvivalButton("head next", "headNextButton"));
        buttonList.add(new ShrineSurvivalButton("beard next", "beardNextButton"));
        buttonList.add(new ShrineSurvivalButton("top next", "topNextButton"));
        buttonList.add(new ShrineSurvivalButton("pants next", "pantsNextButton"));
        buttonList.add(new ShrineSurvivalButton("shoes next", "shoesNextButton"));
        buttonList.add(new ShrineSurvivalButton("class next", "classNextButton"));

        int totalSize = buttonList.size() * 70;
        AtomicReference<Double> buttonStartY = new AtomicReference<>((this.getPane().getHeight() / 2) - (totalSize / 2));
        buttonList.forEach(b -> {
            this.getPane().getChildren().add(b);
            b.setLayoutX(buttonStartX);
            b.setLayoutY(buttonStartY.get());
            buttonStartY.updateAndGet(v -> v + 70);
        });
    }

    private void addStartButton() {
        ShrineSurvivalButton start = new ShrineSurvivalButton("start", "startButton");
        this.getPane().getChildren().add(start);
        start.setLayoutX(this.getPane().getWidth() / 10);
        start.setLayoutY(this.getPane().getHeight() / 1.2);
    }

    private void buttonActions() {
        buttonList.forEach(b -> b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (b.getButtonName().contains("howToPlay") || b.getButtonName().contains("playSolo") || b.getButtonName().contains("playmultiplayer") || b.getButtonName().contains("scores") || b.getButtonName().contains("credits") || b.getButtonName().contains("settings")) {
                    createSubScene(b.getButtonName());
                } else {
                    System.out.println("Error");
                }
            }
        }));

    }

    //TODO: Actions for Character Editor Buttons

}
