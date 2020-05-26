package erik.wiesi.model;

import javafx.scene.canvas.Canvas;

public class Player extends Entity {

    private final String NAME;
    private double health = 100;

    public Player(Canvas canvas, String name) {
        super(canvas);
        this.NAME = name;
    }

}
