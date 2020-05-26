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

    public PlaySoloScene(AnchorPane mainPane, PlayerSprite playerSprite) {

        this.mainPane = mainPane;

        generateSpriteList();
        generateMap();
        setPlayer(playerSprite);


        AnimationTimer test = new Loop();
        test.start();

    }

    private class Loop extends AnimationTimer {
        
        @Override
        public void handle(long now) {
            // TODO: Gameloop
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