package erik.wiesi.view.playSolo;

import erik.wiesi.main.handler.Handler;
import erik.wiesi.model.entities.Enemy;
import erik.wiesi.model.entities.Entity;
import erik.wiesi.model.entities.Player;
import erik.wiesi.sprites.TileMap;
import erik.wiesi.sprites.PlayerSprite;
import erik.wiesi.view.ViewManager;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlaySoloScene{

    private AnchorPane mainPane;
    private TileMap tileMap;

    private final String SPRITESHEET = "/SpriteSheets/roguelikeSheet_transparent.png";
    private List<Integer[]> sprites;
    private final int rescaleFactor = 2;
    private Player player;
    private boolean goUp, goDown, goLeft, goRight;
    private List<Entity> enemyList = new ArrayList<>();

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
        System.out.println(player.getUuid());
        gameLoop.start();

    }

    private class Loop extends AnimationTimer {

        private long start = 0;
        private int fps = 0;
        private long delta;
        private final int PRESSED = 1;
        private int roundCount = 0;
        private int min, max;
        private int randX, randY;
        private final int VIEW_WIDTH = (int) ViewManager.getWIDTH();
        private final int VIEW_HEIGHT = (int) ViewManager.getHEIGHT();
        private double playerPosX, playerPosY;

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

            if (enemyList.size() == 0) {
                roundCount = 10;
                min = roundCount;
                max = (int) roundCount / 5 + min;

//                int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                int randomNum = 1;
                for (int i = 0; i < randomNum; i++) {
                    enemyList.add(i, new Enemy());
                }
                enemyList.forEach(e -> {

                    setRandomBorderPos();
                    mainPane.getChildren().add(e.getCanvas());
                    e.getCanvas().setTranslateX(randX);
                    e.getCanvas().setTranslateY(randY);
                    e.getCanvas().setScaleX(rescaleFactor * 2);
                    e.getCanvas().setScaleY(rescaleFactor * 2);
                });
            }

            int dx = 0, dy = 0;

            if (goUp) dy -= PRESSED;
            if (goDown) dy += PRESSED;
            if (goRight)  dx += PRESSED;
            if (goLeft)  dx -= PRESSED;
            Handler.movement(player, dx, dy);

            playerPosX = player.getCanvas().getTranslateX();
            playerPosY = player.getCanvas().getTranslateY();
            enemyList.forEach(e -> {
                int ex = 0, ey = 0;
                double enemyPosX = e.getCanvas().getTranslateX();
                double enemyPosY = e.getCanvas().getTranslateY();
                if (enemyPosX < playerPosX) ex += 1;
                if (enemyPosX > playerPosX) ex -= 1;
                if (enemyPosY < playerPosY) ey += 1;
                if (enemyPosY > playerPosY) ey -= 1;
                Handler.movement(e, ex, ey, player);
            });
        }

        private void setRandomBorderPos() {
            if (new Random().nextInt(2) < 1) {
                if (new Random().nextInt(2) < 1) { randY = ThreadLocalRandom.current().nextInt(0, (VIEW_HEIGHT / 10)); }
                else { randY = ThreadLocalRandom.current().nextInt(VIEW_HEIGHT - (VIEW_HEIGHT / 10), VIEW_HEIGHT); }
                randX = ThreadLocalRandom.current().nextInt(VIEW_WIDTH);
            } else {
                if (new Random().nextInt(2) < 1) { randX = ThreadLocalRandom.current().nextInt(0, (VIEW_WIDTH / 10)); }
                else { randX = ThreadLocalRandom.current().nextInt(VIEW_WIDTH - (VIEW_WIDTH / 10), VIEW_WIDTH); }
                randY = ThreadLocalRandom.current().nextInt(VIEW_HEIGHT);
            }
        }
    }


    private void generateSpriteList() {
        sprites = new ArrayList<>();
        sprites.add(new Integer[]{3, 7});
        sprites.add(new Integer[]{3, 10});
        sprites.add(new Integer[]{3, 16});
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
        player.getCanvas().setTranslateX(ViewManager.getWIDTH() / 2);
        player.getCanvas().setTranslateY(ViewManager.getHEIGHT() / 2);
    }

}