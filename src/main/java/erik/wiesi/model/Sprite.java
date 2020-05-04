package erik.wiesi.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private Canvas canvas;

    public Sprite(Canvas canvas) {
        this.canvas = canvas;
    }
    public Sprite(Image image) {
        this.canvas = CanvasHelper.imageToCanvas(image);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Sprite addSprite(Sprite sprite) {
        double width = this.canvas.getWidth();
        double height = this.canvas.getHeight();

        Image self = CanvasHelper.canvasToImage(canvas);
        Image other = CanvasHelper.canvasToImage(sprite.canvas);

        Canvas result = new Canvas(width, height);
        GraphicsContext context = result.getGraphicsContext2D();

        context.drawImage(self, 0,0, width, height);
        context.drawImage(other, 0,0, width, height);

        return new Sprite(result);
    }

    public void setScale(int s) {
        canvas.setScaleX(s);
        canvas.setScaleY(s);
    }
}