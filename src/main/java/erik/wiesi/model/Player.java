package erik.wiesi.model;

import javafx.scene.canvas.Canvas;

public class Player extends Entity {

    private final String NAME;
    private double health;

    public Player(Canvas canvas, double posX, double posY, String name) {
        super(canvas, posX, posY);
        this.NAME = name;
        health = 100;

    }
}
