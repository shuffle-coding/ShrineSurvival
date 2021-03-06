package erik.wiesi.view.mainMenu.subScenes;

import erik.wiesi.model.*;
import erik.wiesi.model.characterBuilder.Creator;
import erik.wiesi.statics.Types;
import erik.wiesi.sprites.PlayerSprite;
import erik.wiesi.sprites.Sprite;
import erik.wiesi.sprites.Spritesheet;
import erik.wiesi.view.ViewManager;
import erik.wiesi.view.mainMenu.InfoPanel;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class PlaySoloSubScene extends ShrineSurvivalSubScene {

    private final Spritesheet CHARS = new Spritesheet("/SpriteSheets/roguelikeChar_transparent.png");
    private final List<ShrineSurvivalButton> buttonList = new ArrayList<>();
    private Pane pane = this.getPane();
    ShrineSurvivalTextField nameField;
    private final Sprite previewSprite;
    private Creator body;
    private Creator head;
    private Creator top;
    private Creator pants;
    private Creator shoes;

    /**
     * Creats new PlaySoloSubScene
     * Character Builder Included
     */
    public PlaySoloSubScene() {

        body = new Creator(Types.BODY);
        head= new Creator(Types.HEAD);
        top = new Creator(Types.TOP);
        pants = new Creator(Types.PANTS);
        shoes = new Creator(Types.SHOES);

        previewSprite = new Sprite(CHARS);
        addButtons();
        addStartButton();
        addNameTextField();
        buttonActions();

        previewSprite.addSprite(body.getX(), body.getY());
        previewSprite.addSprite(pants.getX(), pants.getY());
        previewSprite.addSprite(shoes.getX(), shoes.getY());
        previewSprite.addSprite(top.getX(), top.getY());
        previewSprite.addSprite(head.getX(), head.getY());
        pane.getChildren().add(previewSprite.getCanvas());
        previewSprite.setLayout(this.getPane().getWidth() / 4, this.getPane().getHeight() / 2);
        previewSprite.setScale(12);
    }

    private void addButtons() {

        double buttonStartX = this.getPane().getWidth() / 1.6;

        buttonList.add(new ShrineSurvivalButton("body next", "bodyNextButton"));
        buttonList.add(new ShrineSurvivalButton("head next", "headNextButton"));
        buttonList.add(new ShrineSurvivalButton("top next", "topNextButton"));
        buttonList.add(new ShrineSurvivalButton("pants next", "pantsNextButton"));
        buttonList.add(new ShrineSurvivalButton("shoes next", "shoesNextButton"));
//        buttonList.add(new ShrineSurvivalButton("class next", "classNextButton"));

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

        start.setOnAction(actionEvent -> {
            if (nameField.getText().equals("")) {
                noPlayerName();
            } else {
                ViewManager.switchToPlaySoloScene(new PlayerSprite(CHARS, body.getXY(), pants.getXY(), shoes.getXY(), top.getXY(), head.getXY()), nameField.getText());
            }
        });

        pane.getChildren().add(start);
        start.setLayoutX(pane.getWidth() / 10);
        start.setLayoutY(pane.getHeight() / 10 * 8);
    }

    private void addNameTextField() {
        nameField = new ShrineSurvivalTextField("name");

        pane.getChildren().add(nameField);
        nameField.setLayoutX(pane.getWidth() / 10);
        nameField.setLayoutY(pane.getHeight() / 10 * 2);
    }

    private void noPlayerName() {
        InfoPanel text = new InfoPanel("Input Name to Start!");
        pane.getChildren().add(text);
        text.setLayoutX(pane.getWidth() / 10);
        text.setLayoutY(pane.getHeight() / 10);

    }

    private void buttonActions() {
        buttonList.forEach(b -> b.setOnAction(actionEvent -> buttonEvent(b.getButtonName())));

    }

    private void buttonEvent(String buttonName) {
        pane.getChildren().removeAll(previewSprite.getCanvas());
        previewSprite.resetCanvas();
        switch (buttonName) {
            case "bodyNextButton" -> body.getNext();
            case "headNextButton" -> head.getNext();
            case "topNextButton" -> top.getNext();
            case "pantsNextButton" -> pants.getNext();
            case "shoesNextButton" -> shoes.getNext();
            default -> System.out.println("Error");
        }

        previewSprite.addSprite(body.getX(), body.getY());
        previewSprite.addSprite(pants.getX(), pants.getY());
        previewSprite.addSprite(shoes.getX(), shoes.getY());
        previewSprite.addSprite(top.getX(), top.getY());
        previewSprite.addSprite(head.getX(), head.getY());
        pane.getChildren().add(previewSprite.getCanvas());
        previewSprite.setLayout(this.getPane().getWidth() / 4, this.getPane().getHeight() / 2);
        previewSprite.setScale(12);

    }


}
