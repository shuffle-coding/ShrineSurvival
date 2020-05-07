package erik.wiesi.model.subScenes;

import erik.wiesi.model.*;
import erik.wiesi.model.characterBuilder.Creator;
import erik.wiesi.model.characterBuilder.Types;
import erik.wiesi.model.characters.PlayerSprite;
import erik.wiesi.view.ViewManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class PlaySoloSubScene extends ShrineSurvivalSubScene {


    private final Spritesheet CHARS = new Spritesheet("/SpriteSheets/roguelikeChar_transparent.png");
    private final String BACKGROUND = "/Background/blue_panel.png";
    private List<ShrineSurvivalButton> buttonList = new ArrayList<>();
    Pane pane = this.getPane();
    private Sprite previewSprite;
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

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new PlayerSprite(CHARS, body.getXY(), pants.getXY(), shoes.getXY(), top.getXY(), head.getXY());
                ViewManager.switchToPlaySoloScene();
            }
        });

        pane.getChildren().add(start);
        start.setLayoutX(this.getPane().getWidth() / 10);
        start.setLayoutY(this.getPane().getHeight() / 1.2);
    }

    private void buttonActions() {
        buttonList.forEach(b -> b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonEvent(b.getButtonName());
            }
        }));

    }

    private void buttonEvent(String buttonName) {
        pane.getChildren().removeAll(previewSprite.getCanvas());
        previewSprite.resetCanvas();
        switch (buttonName) {
            case "bodyNextButton":
                body.getNext();
                break;
            case "headNextButton":
                head.getNext();
                break;
            case "topNextButton":
                top.getNext();
                break;
            case "pantsNextButton":
                pants.getNext();
                break;
            case "shoesNextButton":
                shoes.getNext();
                break;
            case "classNextButton":
                // TODO: Implementation
                System.out.println("Not implemented Yet");
                break;
            default:
                System.out.println("Error");
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
