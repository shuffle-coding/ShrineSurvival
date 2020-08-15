package erik.wiesi.view.playSolo;

import erik.wiesi.model.ShrineSurvivalButton;
import erik.wiesi.view.mainMenu.InfoPanel;
import erik.wiesi.model.ShrineSurvivalSubScene;
import erik.wiesi.view.playSolo.handler.Handler;
import erik.wiesi.model.entities.Enemy;
import erik.wiesi.model.entities.Entity;
import erik.wiesi.model.entities.Player;
import erik.wiesi.sprites.TileMap;
import erik.wiesi.sprites.PlayerSprite;
import erik.wiesi.view.ViewManager;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

public class PlaySoloScene {

    private static AnchorPane mainPane;
    private TileMap tileMap;

    private final String SPRITESHEET = "/SpriteSheets/roguelikeSheet_transparent.png";
    private final String panelBackground = "/Background/yellow_panel.png";
    private static Score score;
    private List<Integer[]> sprites;
    private final int rescaleFactor = 2;
    private static PlayerSprite playerSprite;
    private Player player;
    private boolean goUp, goDown, goLeft, goRight, attackUp, attackDown, attackLeft, attackRight;
    private List<Entity> entities = new ArrayList<>();
    private final InfoPanel healthBar;
    private static AnimationTimer gameLoop;

    /**
     * @param mainPane AnchorPane to be drawn at
     * @param playerModel Generated PlayerSprite chosen by Player
     * @param playerName player's chosen name
     */
    public PlaySoloScene(AnchorPane mainPane, PlayerSprite playerModel, String playerName) {

        PlaySoloScene.mainPane = mainPane;

        playerSprite = playerModel;
        generateSpriteList();
        generateMap();
        score = new Score(playerName);
        setPlayer();
        Handler.setMainPane(mainPane);

        mainPane.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> goUp = true;
                case DOWN -> goDown = true;
                case LEFT -> goLeft = true;
                case RIGHT -> goRight = true;
                case W -> attackUp = true;
                case S -> attackDown = true;
                case A -> attackLeft = true;
                case D -> attackRight = true;
            }
        });

        mainPane.getScene().setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP -> goUp = false;
                case DOWN -> goDown = false;
                case LEFT -> goLeft = false;
                case RIGHT -> goRight = false;
                case W -> attackUp = false;
                case S -> attackDown = false;
                case A -> attackLeft = false;
                case D -> attackRight = false;
            }
        });
        ImageView scorePanel = new ImageView(new Image(getClass().getResource(panelBackground).toString(), 150, 100, false, false));
        ImageView healthPanel = new ImageView(new Image(getClass().getResource(panelBackground).toString(), 150, 50, false, false));
        healthBar = new InfoPanel(Integer.toString(player.getHealth()));
        mainPane.getChildren().add(scorePanel);
        scorePanel.setLayoutX(1400);
        scorePanel.setLayoutY(50);
        mainPane.getChildren().add(healthPanel);
        healthPanel.setLayoutX(50);
        healthPanel.setLayoutY(50);
        mainPane.getChildren().add(score.getScorePanel());
        score.getScorePanel().setLayoutX(1420);
        score.getScorePanel().setLayoutY(50);
        score.getScorePanel().setPrefSize(100, 50);
        mainPane.getChildren().add(score.getWavesPanel());
        score.getWavesPanel().setLayoutX(1420);
        score.getWavesPanel().setLayoutY(100);
        score.getWavesPanel().setPrefSize(120,  50);
        mainPane.getChildren().add(healthBar);
        healthBar.setLayoutX(70);
        healthBar.setLayoutY(50);
        healthBar.setPrefSize(120, 50);
        gameLoop = new Loop();
        gameLoop.start();
    }

    private class Loop extends AnimationTimer {

        private long start = 0;
        private int fps = 0;
        private long delta;
        private int roundCount = 0;
        private int min, max;
        private final int maxEnemies = 30;
        private int randX, randY;
        private final int VIEW_WIDTH = (int) ViewManager.getWIDTH();
        private final int VIEW_HEIGHT = (int) ViewManager.getHEIGHT();
        private boolean attack = false;
        private long attackStart;
        private long attackPause = 0;

        /**
         * Handler of the Gameloop, refreshes as often as possible but not more often as the frequency of the monitor
         * @param now gets System Time in nanoseconds, does not need to be specified
         */
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

            if (entities.size() == 1) {
                roundCount++;
                min = roundCount;
                max = (roundCount / 5) + min;
                score.addWave();

                int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                if (randomNum > maxEnemies) {
                    randomNum = maxEnemies;
                }
                for (int i = 0; i < randomNum; i++) {
                    entities.add(i + 1, new Enemy());
                }
                entities.stream().filter(entity -> entity.getUuid() != player.getUuid()).forEach(e -> {
                    setRandomBorderPos();
                    mainPane.getChildren().add(e.getCanvas());
                    e.getCanvas().setTranslateX(randX);
                    e.getCanvas().setTranslateY(randY);
                    e.getCanvas().setScaleX(rescaleFactor * 2);
                    e.getCanvas().setScaleY(rescaleFactor * 2);
                });
                Handler.setEntities(entities);
            }
            entities = Handler.getEntities();

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
                Handler.drawWeapon(player, ax, ay, 200);
            } else if (attack && ((now - attackStart) / 1000000) >= 200) {
                Handler.removeWeapon(player);
                attack = false;
                attackPause = now;
            } else if (attack) {
                Handler.attack(player, score);
            }


            entities.forEach(entity -> {
                if (!entity.equals(player)) {
                    Handler.movement(entity, player);
                }
            });

            healthBar.setText(Integer.toString(player.getHealth()));
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
        mainPane.getChildren().add(tileMap.getCanvas());
        tileMap.getCanvas().setLayoutX(ViewManager.getWIDTH() / (rescaleFactor * rescaleFactor));
        tileMap.getCanvas().setLayoutY(ViewManager.getHEIGHT() / (rescaleFactor * rescaleFactor));
    }

    private void setPlayer() {
        this.player = new Player(playerSprite.getCanvas());
        mainPane.getChildren().add(player.getCanvas());
        player.getCanvas().setScaleX(rescaleFactor * 2);
        player.getCanvas().setScaleY(rescaleFactor * 2);
        player.getCanvas().setTranslateX(ViewManager.getWIDTH() / 2);
        player.getCanvas().setTranslateY(ViewManager.getHEIGHT() / 2);
        entities.add(0, player);
        Handler.setPlayer(player);
    }

    /**
     * Generated Panel at the end of the Game
     * Shows Score and changes to menuScene with button press
     */
    public static void gameOver() {
        gameLoop.stop();
        score.setPlaytime();

        try {
            Handler.sendData(playerSprite, score);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ShrineSurvivalSubScene gameEndPanel = new ShrineSurvivalSubScene();
        mainPane.getChildren().add(gameEndPanel);
        gameEndPanel.setLayoutX((ViewManager.getWIDTH() / 2) - (gameEndPanel.getWidth() / 2));
        gameEndPanel.setLayoutY((ViewManager.getHEIGHT() / 2) - (gameEndPanel.getHeight() / 2));
        gameEndPanel.setOpacity(0.7);

        Map<InfoPanel, InfoPanel> panels = new HashMap<>();
        panels.put(new InfoPanel("Time Played:"), new InfoPanel(score.getPlayTime()));
        panels.put(new InfoPanel("Score: "), new InfoPanel(Integer.toString(score.getScore())));
        panels.put(new InfoPanel("Wave: "), new InfoPanel(Integer.toString(score.getWaves())));
        panels.put(new InfoPanel("Defeated Enemies: "), new InfoPanel(Integer.toString(score.getDefeatedEnemies())));
        int totalSize = panels.size() * 70;
        AtomicReference<Double> panelStartY = new AtomicReference<>((mainPane.getHeight() / 2) - (totalSize / 2));
        panels.forEach((panel, value) -> {
            double panelStartX = (ViewManager.getWIDTH() / 3) - (panel.getWidth() / 2);
            double valueStartX = (ViewManager.getWIDTH() / 1.8) - (value.getWidth() / 2);
            mainPane.getChildren().add(panel);
            mainPane.getChildren().add(value);
            panel.setLayoutX(panelStartX);
            panel.setLayoutY(panelStartY.get());
            value.setLayoutX(valueStartX);
            value.setLayoutY(panelStartY.get());
            panelStartY.updateAndGet(v -> v + 70);
        });
        InfoPanel title = new InfoPanel("Score:");
        mainPane.getChildren().add(title);
        title.setPrefWidth(150);
        title.setLayoutX((ViewManager.getWIDTH() / 2) - (title.getPrefWidth() / 2));
        title.setLayoutY(ViewManager.getHEIGHT() / 8 * 1.8);
        title.setFontSize(30);
        ShrineSurvivalButton backToMenu = new ShrineSurvivalButton("Back To Menu", "backToMenuButton");
        mainPane.getChildren().add(backToMenu);
        backToMenu.setLayoutX((ViewManager.getWIDTH() / 2) - (backToMenu.getPrefWidth() / 2));
        backToMenu.setLayoutY(ViewManager.getHEIGHT() / 8 * 5.8);
        backToMenu.setOnAction(actionEvent -> ViewManager.switchToMenuScene());
    }
}