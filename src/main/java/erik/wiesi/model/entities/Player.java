package erik.wiesi.model.entities;

import javafx.scene.canvas.Canvas;

public class Player extends Entity {

    private final String NAME;

    public Player(Canvas canvas, String name) {
        super();
        this.setCanvas(canvas);
        this.setHealth(100);
        this.NAME = name;
    }

}
