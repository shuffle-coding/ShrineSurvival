package erik.wiesi.view.playSolo;

import erik.wiesi.model.Player;
import erik.wiesi.model.TileMap;
import erik.wiesi.model.characters.PlayerSprite;
import erik.wiesi.view.ViewManager;
import javafx.animation.AnimationTimer;
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
    private boolean goUp, goDown, goLeft, goRight;

    public PlaySoloScene(AnchorPane mainPane, PlayerSprite playerSprite) {

        this.mainPane = mainPane;

        generateSpriteList();
        generateMap();
        setPlayer(playerSprite);

        mainPane.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    goUp = true; break;
                case DOWN:  goDown = true; break;
                case LEFT:  goLeft = true; break;
                case RIGHT: goRight = true; break;
            }
        });

        mainPane.getScene().setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    goUp = false; break;
                case DOWN:  goDown = false; break;
                case LEFT:  goLeft = false; break;
                case RIGHT: goRight = false; break;
            }
        });

        AnimationTimer gameLoop = new Loop();
        gameLoop.start();

    }

    private class Loop extends AnimationTimer {

        private long start = 0;
        private int fps = 0;
        private long delta;
        private final int PRESSED = 1;

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

            if (goUp) dy -= PRESSED;
            if (goDown) dy += PRESSED;
            if (goRight)  dx += PRESSED;
            if (goLeft)  dx -= PRESSED;

            player.movement(dx, dy);

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
        this.player = new Player(playerSprite.getCanvas(), "Klaus");
        mainPane.getChildren().add(player.getCanvas());
        player.getCanvas().setScaleX(rescaleFactor * 2);
        player.getCanvas().setScaleY(rescaleFactor * 2);
        player.getCanvas().setTranslateX(0);
        player.getCanvas().setTranslateY(0);
    }


}