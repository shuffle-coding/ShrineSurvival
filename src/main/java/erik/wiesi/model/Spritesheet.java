package erik.wiesi.model;

import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;

public class Spritesheet {

    private int tilesize;
    private int spaceBetweenTiles;

    private final Image image;

    public Spritesheet(String url, int tilesize, int spaceBetweenTiles) {
        this.tilesize = tilesize;
        this.spaceBetweenTiles = spaceBetweenTiles;
        image = new Image(getClass().getResource(url).toString());
    }

    public Canvas getSprite(int x, int y) {
        Canvas canvas = new Canvas(tilesize, tilesize);

        double startX = x * (tilesize + spaceBetweenTiles);
        double startY = y * (tilesize + spaceBetweenTiles);

        canvas.getGraphicsContext2D().drawImage(image, startX, startY, startX + tilesize, startY + tilesize, 0, 0, tilesize, tilesize);

        return canvas;
    }

}