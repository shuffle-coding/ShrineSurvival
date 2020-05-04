package erik.wiesi.model;

import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;

public class Spritesheet {
    private final int DEFAULT_TILE_SIZE = 16;
    private final int DEFAULT_MARGIN = 2;

    private int tilesize;
    public int getTilesize() {
        return tilesize;
    }

    private int margin;
    public int getMargin() {
        return margin;
    }

    private final Image image;
    public Spritesheet(String url) {
        this.tilesize = DEFAULT_TILE_SIZE;
        this.margin = DEFAULT_MARGIN;
        image = new Image(getClass().getResource(url).toString());
    }

    public Image getImage() {
        return this.image;
    }

    public Spritesheet(String url, int tilesize) {
        this.tilesize = tilesize;
        this.margin = DEFAULT_MARGIN;
        image = new Image(getClass().getResource(url).toString());
    }

    public Spritesheet(String url, int tilesize, int spaceBetweenTiles) {
        this.tilesize = tilesize;
        this.margin = spaceBetweenTiles;
        image = new Image(getClass().getResource(url).toString());
    }

    public Sprite getSprite(int x, int y) {
        Canvas canvas = new Canvas(tilesize, tilesize);

        double startX = x * (tilesize + margin);
        double startY = y * (tilesize + margin);

        canvas.getGraphicsContext2D().drawImage(image, startX, startY, startX + tilesize, startY + tilesize, 0, 0, tilesize, tilesize);

        return new Sprite(canvas);
    }

}