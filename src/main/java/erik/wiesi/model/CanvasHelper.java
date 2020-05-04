package erik.wiesi.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class CanvasHelper {
    static Image canvasToImage(Canvas canvas) {
        WritableImage image = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
        canvas.snapshot(null, image);
        return image;
    }
    static Canvas imageToCanvas(Image image) {
        double width = image.getWidth();
        double height = image.getHeight();
        Canvas canvas = new Canvas(width, height);
        canvas.getGraphicsContext2D().drawImage(image, 0, 0, width, height);
        return canvas;
    }
}