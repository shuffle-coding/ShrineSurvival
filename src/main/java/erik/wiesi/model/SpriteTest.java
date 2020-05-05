package erik.wiesi.model;

import javafx.scene.canvas.Canvas;

public class SpriteTest {

    private Canvas canvas;
    private SpritesheetTest spritesheet;

    public SpriteTest(SpritesheetTest spritesheet) {
        this.spritesheet = spritesheet;
        canvas = new Canvas(spritesheet.getTilesize(), spritesheet.getTilesize());
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void resetCanvas() {
        canvas = null;
        canvas = new Canvas(spritesheet.getTilesize(), spritesheet.getTilesize());
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
