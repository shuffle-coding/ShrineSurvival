package erik.wiesi.view.playSolo;

import erik.wiesi.view.playSolo.handler.Handler;
import erik.wiesi.model.entities.Enemy;
import erik.wiesi.model.entities.Entity;
import erik.wiesi.model.entities.Player;
import erik.wiesi.sprites.TileMap;
import erik.wiesi.sprites.PlayerSprite;
import erik.wiesi.view.ViewManager;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlaySoloScene {

    private AnchorPane mainPane;
    private TileMap tileMap;

    private final String SPRITESHEET = "/SpriteSheets/roguelikeSheet_transparent.png";
    private List<Integer[]> sprites;
    private final int rescaleFactor = 2;
    private Player player;
    private boolean goUp, goDown, goLeft, goRight, attackUp, attackDown, attackLeft, attackRight;
    private List<Entity> enemyList = new ArrayList<>();

    public PlaySoloScene(AnchorPane mainPane, PlayerSprite playerSprite) {

        this.mainPane = mainPane;

        generateSpriteList();
        generateMap();
        setPlayer(playerSprite);
        Handler.setMainPane(mainPane);

        mainPane.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    goUp = true;
                    break;
                case DOWN:
                    goDown = true;
                    break;
                case LEFT:
                    goLeft = true;
                    break;
                case RIGHT:
                    goRight = true;
                    break;
                case W:
                    attackUp = true;
                    break;
                case S:
                    attackDown = true;
                    break;
                case A:
                    attackLeft = true;
                    break;
                case D:
                    attackRight = true;
                    break;
            }
        });

        mainPane.getScene().setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    goUp = false;
                    break;
                case DOWN:
                    goDown = false;
                    break;
                case LEFT:
                    goLeft = false;
                    break;
                case RIGHT:
                    goRight = false;
                    break;
                case W:
                    attackUp = false;
                    break;
                case S:
                    attackDown = false;
                    break;
                case A:
                    attackLeft = false;
                    break;
                case D:
                    attackRight = false;
                    break;
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
        private int roundCount = 0;
        private int min, max;
        private int randX, randY;
        private final int VIEW_WIDTH = (int) ViewManager.getWIDTH();
        private final int VIEW_HEIGHT = (int) ViewManager.getHEIGHT();
        private boolean attack = false;
        private long attackStart;
        private PathTransition attackArc = null;
        private long attackPause = 0;

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

            if (enemyList.size() == 1) {
                roundCount++;
                min = roundCount;
                max = (int) roundCount / 5 + min;

                int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                for (int i = 0; i < randomNum; i++) {
                    enemyList.add(i + 1, new Enemy());
                }
                enemyList.stream().filter(entity -> entity.getUuid() != player.getUuid()).forEach(e -> {
                    setRandomBorderPos();
                    mainPane.getChildren().add(e.getCanvas());
                    e.getCanvas().setTranslateX(randX);
                    e.getCanvas().setTranslateY(randY);
                    e.getCanvas().setScaleX(rescaleFactor * 2);
                    e.getCanvas().setScaleY(rescaleFactor * 2);
                });
                Handler.setEntities(enemyList);
            }
            enemyList = Handler.getEntities();

            int dx = 0, dy = 0;
            if (goUp) dy -= 1;
            if (goDown) dy += 1;
            if (goRight) dx += 1;
            if (goLeft) dx -= 1;
            Handler.movement(player, dx, dy);

            int ax = 0, ay = 0;

            if (attackLeft) ax = -1;
            else if (attackRight) ax = 1;
            if (attackUp) ay = -1;
            else if (attackDown) ay = 1;

            if (!attack && (ax != 0 || ay != 0) && ((now - attackPause) / 1000000) >= 200 ) {
                attackStart = now;
                attack = true;
                attackArc =  Handler.drawWeapon(player, ax, ay, 200);
            } else if (attack && ((now - attackStart) / 1000000) >= 200) {
                Handler.removeWeapon(player);
                attack = false;
                attackPause = now;
            } else if (attack) {
//                Handler.repositionWeapon(player, attackArc);
                Handler.attack(player);
            }


            enemyList.forEach(entity -> {
                if (!entity.equals(player)) {
                    Handler.movement(entity, player);
                }
            });
        }

        private void setRandomBorderPos() {
            if (new Random().nextInt(2) < 1) {
                if (new Random().nextInt(2) < 1) {
                    randY = ThreadLocalRandom.current().nextInt(0, (VIEW_HEIGHT / 10));
                } else {
                    randY = ThreadLocalRandom.current().nextInt(VIEW_HEIGHT - (VIEW_HEIGHT / 10), VIEW_HEIGHT);
                }
                randX = ThreadLocalRandom.current().nextInt(VIEW_WIDTH);
            } else {
                if (new Random().nextInt(2) < 1) {
                    randX = ThreadLocalRandom.current().nextInt(0, (VIEW_WIDTH / 10));
                } else {
                    randX = ThreadLocalRandom.current().nextInt(VIEW_WIDTH - (VIEW_WIDTH / 10), VIEW_WIDTH);
                }
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
        enemyList.add(0, player);
        Handler.setPlayer(player);
    }

}