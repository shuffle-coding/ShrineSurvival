package erik.wiesi.model;

import javafx.scene.canvas.Canvas;

public class Sprite {

    private final Canvas canvas;

    public Sprite(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Canvas addSprite(Spritesheet spritesheet, int x, int y) {

        int tilesize = spritesheet.getTilesize();
        int margin = spritesheet.getMargin();

        double startX = x * (tilesize + margin);
        double startY = y * (tilesize + margin);

        canvas.getGraphicsContext2D().drawImage(spritesheet.getImage(), startX, startY, startX + tilesize, startY + tilesize, 0, 0, tilesize, tilesize);
        return canvas;
    }

}