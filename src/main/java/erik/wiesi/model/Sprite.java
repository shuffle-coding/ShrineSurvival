package erik.wiesi.model;

import javafx.scene.canvas.Canvas;

public class Sprite {

    private Canvas canvas;

    public Sprite(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Canvas addSprite(Spritesheet spritesheet, int x, int y) {

        double tilesize = spritesheet.getTilesize();
        double margin = spritesheet.getMargin();

        double startX = x * (tilesize + margin);
        double startY = y * (tilesize + margin);

        canvas.getGraphicsContext2D().drawImage(spritesheet.getImage(), startX, startY, tilesize, tilesize, 0, 0, tilesize, tilesize);
        return canvas;
    }
    public Canvas setScale(int s) {

        this.canvas.setScaleX(s);
        this.canvas.setScaleY(s);

        return canvas;
    }

}