package erik.wiesi.view.mainMenu.subScenes;

import erik.wiesi.model.*;
import erik.wiesi.model.characterBuilder.Creator;
import erik.wiesi.statics.Types;
import erik.wiesi.sprites.PlayerSprite;
import erik.wiesi.sprites.Sprite;
import erik.wiesi.sprites.Spritesheet;
import erik.wiesi.view.ViewManager;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class PlaySoloSubScene extends ShrineSurvivalSubScene {

    // TODO: JavaDoc

    private final Spritesheet CHARS = new Spritesheet("/SpriteSheets/roguelikeChar_transparent.png");
    private final List<ShrineSurvivalButton> buttonList = new ArrayList<>();
    Pane pane = this.getPane();
    private final Sprite previewSprite;
    Creator body;
    Creator head;
    Creator top;
    Creator pants;
    Creator shoes;

    public PlaySoloSubScene() {

        body = new Creator(Types.BODY);
        head= new Creator(Types.HEAD);
        top = new Creator(Types.TOP);
        pants = new Creator(Types.PANTS);
        shoes = new Creator(Types.SHOES);

        previewSprite = new Sprite(CHARS);
        addButtons();
        addStartButton();
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

        start.setOnAction(actionEvent -> ViewManager.switchToPlaySoloScene(new PlayerSprite(CHARS, body.getXY(), pants.getXY(), shoes.getXY(), top.getXY(), head.getXY())));

        pane.getChildren().add(start);
        start.setLayoutX(this.getPane().getWidth() / 10);
        start.setLayoutY(this.getPane().getHeight() / 1.2);
    }

    private void buttonActions() {
        buttonList.forEach(b -> b.setOnAction(actionEvent -> buttonEvent(b.getButtonName())));

    }

    private void buttonEvent(String buttonName) {
        pane.getChildren().removeAll(previewSprite.getCanvas());
        previewSprite.resetCanvas();
        //            case "classNextButton":
        //                TODO: Implementation
        //                System.out.println("Not implemented Yet");
        //                break;
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
