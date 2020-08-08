package erik.wiesi.sprites;

import javafx.scene.canvas.Canvas;

public class Sprite {

    private Canvas canvas;
    private final Spritesheet spritesheet;

    /**
     * create new empty Sprite with given {@link Spritesheet},
     * Size of the Canvas is defined by the TileSize of the given {@link Spritesheet}
     * @param spritesheet {@link Spritesheet} used as reference for this Sprite
     */
    public Sprite(Spritesheet spritesheet) {
        this.spritesheet = spritesheet;
        canvas = new Canvas(spritesheet.getTilesize(), spritesheet.getTilesize());
    }

    /**
     * Create new empty Sprite with given {@link Spritesheet}
     * Size of the Canvas is defined by given width and height
     * @param spritesheet spritesheet {@link Spritesheet} used as reference for this Sprite
     * @param width defines width of the new Canvas
     * @param height defines height of the new Canvas
     */
    public Sprite(Spritesheet spritesheet, int width, int height) {
        this.spritesheet = spritesheet;
        canvas = new Canvas(width, height);
    }

    /**
     * @return returns this Sprite's Canvas
     */
    public Canvas getCanvas() {
        return this.canvas;
    }

    /**
     * sets this Sprite's Canvas to NULL and recreats the Canvas
     */
    public void resetCanvas() {
        int x = (int) canvas.getWidth();
        int y = (int) canvas.getHeight();
        canvas = null;
        canvas = new Canvas(x, y);
    }

    /**
     * Adds a new Sprite as a top Layer to this Sprite's Canvas
     * x and y counter have to be defined referencing this Sprite's {@link Spritesheet}
     * @param x defines x Counter from left to right for the chosen Sprite on this Sprite's predefined {@link Spritesheet}
     * @param y defines y Counter from top to bottom for the chosen Sprite on this Sprite's predefined {@link Spritesheet}
     */
    public void addSprite(int x, int y) {

        double tilesize = spritesheet.getTilesize();
        double margin = spritesheet.getMargin();

        double startX = x * (tilesize + margin);
        double startY = y * (tilesize + margin);

        if (x > 1) {
            startX += margin;
        }

        canvas.getGraphicsContext2D().drawImage(spritesheet.getImage(), startX, startY, tilesize, tilesize, 0, 0, tilesize, tilesize);
    }

    /**
     * * Adds a new Sprite as a top Layer to this Sprite's Canvas
     * x and y counter have to be defined referencing this Sprite's {@link Spritesheet}
     * cx and cy are the Position on the Canvas to be drawn at this Canvas, considers tilesize
     * @param x defines x Counter from left to right for the chosen Sprite on this Sprite's predefined {@link Spritesheet}
     * @param y defines y Counter from top to bottom for the chosen Sprite on this Sprite's predefined {@link Spritesheet}
     * @param cx defines x counter to be drawn at this Canvas, considers tilesize
     * @param cy defines y counter to be drawn at this Canvas, considers tilesize
     */
    public void addSprite(int x, int y, int cx, int cy) {

        double tilesize = spritesheet.getTilesize();
        double margin = spritesheet.getMargin();

        double startX = x * (tilesize + margin);
        double startY = y * (tilesize + margin);

        double canvasX = cx * tilesize;
        double canvasY = cy * tilesize;

        canvas.getGraphicsContext2D().drawImage(spritesheet.getImage(), startX, startY, tilesize, tilesize, canvasX, canvasY, tilesize, tilesize);
    }

    /**
     * this method defines total scale of this Sprite for x and y at the same time
     * @param s defines total scale
     */
    public void setScale(int s) {
        canvas.setScaleX(s);
        canvas.setScaleY(s);
    }

    /**
     * Simple method for defining x and y position on the pane at the same time
     * @param x defines x position
     * @param y defines y position
     */
    public void setLayout(double x, double y) {
        canvas.setLayoutX(x);
        canvas.setLayoutY(y);
    }

}
