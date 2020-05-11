package erik.wiesi.model;

import javafx.scene.canvas.Canvas;

public class Sprite {

    private Canvas canvas;
    private Spritesheet spritesheet;

    public Sprite(Spritesheet spritesheet) {
        this.spritesheet = spritesheet;
        canvas = new Canvas(spritesheet.getTilesize(), spritesheet.getTilesize());
    }

    public Sprite(Spritesheet spritesheet, int x, int y) {
        this.spritesheet = spritesheet;
        canvas = new Canvas(x, y);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void resetCanvas() {
        int x = (int) canvas.getWidth();
        int y = (int) canvas.getHeight();
        canvas = null;
        canvas = new Canvas(x, y);
    }

    public Canvas addSprite(int x, int y) {

        double tilesize = spritesheet.getTilesize();
        double margin = spritesheet.getMargin();

        double startX = x * (tilesize + margin);
        double startY = y * (tilesize + margin);

        if (x > 1) {
            startX += margin;
        }

        canvas.getGraphicsContext2D().drawImage(spritesheet.getImage(), startX, startY, tilesize, tilesize, 0, 0, tilesize, tilesize);
        return canvas;
    }

    public Canvas addSprite(int x, int y, int cx, int cy) {

        double tilesize = spritesheet.getTilesize();
        double margin = spritesheet.getMargin();

        double startX = x * (tilesize + margin);
        double startY = y * (tilesize + margin);

        double canvasX = cx * tilesize;
        double canvasY = cy * tilesize;

        canvas.getGraphicsContext2D().drawImage(spritesheet.getImage(), startX, startY, tilesize, tilesize, canvasX, canvasY, tilesize, tilesize);
        return canvas;
    }

    public Canvas setScale(int s) {

        this.canvas.setScaleX(s);
        this.canvas.setScaleY(s);

        return canvas;
    }

    public void setLayout(double x, double y) {
        canvas.setLayoutX(x);
        canvas.setLayoutY(y);
    }

}
