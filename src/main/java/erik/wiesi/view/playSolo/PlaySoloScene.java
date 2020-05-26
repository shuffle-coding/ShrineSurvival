package erik.wiesi.view.playSolo;

import erik.wiesi.model.Player;
import erik.wiesi.model.TileMap;
import erik.wiesi.model.characters.PlayerSprite;
import erik.wiesi.view.ViewManager;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class PlaySoloScene{

    private AnchorPane mainPane;
    private TileMap tileMap;

    private final String SPRITESHEET = "/SpriteSheets/roguelikeSheet_transparent.png";
    private List<Integer[]> sprites;
    private final int rescaleFactor = 2;
    private Player player;
    private boolean goNorth, goSouth, goWest, goEast;
    private final int MOVEMENT_SPEED = 2;

    public PlaySoloScene(AnchorPane mainPane, PlayerSprite playerSprite) {

        this.mainPane = mainPane;

        generateSpriteList();
        generateMap();
        setPlayer(playerSprite);

        mainPane.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = true; break;
                case DOWN:  goSouth = true; break;
                case LEFT:  goWest  = true; break;
                case RIGHT: goEast  = true; break;
            }
        });

        mainPane.getScene().setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = false; break;
                case DOWN:  goSouth = false; break;
                case LEFT:  goWest  = false; break;
                case RIGHT: goEast  = false; break;
            }
        });

        AnimationTimer test = new Loop();
        test.start();

    }

    private class Loop extends AnimationTimer {

        private long start = 0;
        private int fps = 0;
        private long delta;

        @Override
        public void handle(long now) {
            if (start <= 0) {
                start = now;
            }
            delta = now - start;
            fps++;
            if ((delta / 1000000000) >= 1) {
                start = 0;
                System.out.println(fps);
                fps = 0;
            }

            int dx = 0, dy = 0;

            if (goNorth) dy -= MOVEMENT_SPEED;
            if (goSouth) dy += MOVEMENT_SPEED;
            if (goEast)  dx += MOVEMENT_SPEED;
            if (goWest)  dx -= MOVEMENT_SPEED;

            player.getCanvas().setTranslateX(player.getCanvas().getTranslateX() + dx);
            player.getCanvas().setTranslateY(player.getCanvas().getTranslateY() + dy);

        }
    }


    private void generateSpriteList() {
        sprites = new ArrayList<>();
        Integer[] tempInt = new Integer[]{3, 7};
        sprites.add(tempInt);
        Integer[] tempInt1 = new Integer[]{3, 10};
        sprites.add(tempInt1);
        Integer[] tempInt2 = new Integer[]{3, 16};
        sprites.add(tempInt2);
        Integer[] tempInt3 = new Integer[]{3, 16};
        sprites.add(tempInt3);
    }

    private void generateMap() {
        tileMap = new TileMap(SPRITESHEET, sprites, rescaleFactor);
        mainPane.getChildren().add(tileMap.getSprite());
        tileMap.getSprite().setLayoutX(ViewManager.getWIDTH() / (rescaleFactor * rescaleFactor));
        tileMap.getSprite().setLayoutY(ViewManager.getHEIGHT() / (rescaleFactor * rescaleFactor));
    }

    private void setPlayer(PlayerSprite playerSprite) {
        this.player = new Player(playerSprite.getCanvas(), ViewManager.getWIDTH() / 2, ViewManager.getHEIGHT() / 2, "Klaus");
        mainPane.getChildren().add(player.getCanvas());
        player.getCanvas().setScaleX(rescaleFactor * 2);
        player.getCanvas().setScaleY(rescaleFactor * 2);
        player.getCanvas().setTranslateX(0);
        player.getCanvas().setTranslateY(0);
    }


}