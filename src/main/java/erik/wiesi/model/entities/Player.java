package erik.wiesi.model.entities;

import javafx.scene.canvas.Canvas;

public class Player extends Entity {

    private final String NAME;

    public Player(Canvas canvas, String name) {
        super();
        this.canvas = canvas;
        this.health = 100;
        this.NAME = name;
    }

}
