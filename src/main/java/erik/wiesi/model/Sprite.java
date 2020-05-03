package erik.wiesi.model;

import javafx.scene.image.Image;

import java.awt.*;

public class Sprite {

    private final int TILE_SIZE = 16;
    private String url;
    private Image SPRITESHEET = new Image(getClass().getResource(url).toString());
    final double WIDTH = SPRITESHEET.getWidth();
    final double HEIGHT = SPRITESHEET.getHeight();

    public Sprite(Sprites url) {

        this.url = url.getUrl();

    }
                // TODO: Nothing Works

    public Image getSprite(Sprites sprite, int xGrid, int yGrid) {

        return SPRITESHEET.(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public enum Sprites {
        CHARACTER_SHEET("/SpriteSheets/roguelikeChar_transparent.png"),
        MAP_SHEET("/SpriteSheets/roguelikeSheet_transparent.png");

        private String url;

        Sprites(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }
    }

}