package erik.wiesi.model.entities;

import erik.wiesi.main.handler.Handler;
import javafx.scene.canvas.Canvas;

import java.util.UUID;

public abstract class Entity {

    Canvas canvas;
    private final UUID uuid;
    double health;                  // Initialized in Child Constructor
    double MOVEMENT_SPEED = 5;      // Default MOVEMENT_SPEED

    public Entity () {
        this.uuid = UUID.randomUUID();
    }

    public void movement(double dx, double dy) {
        dx = dx * MOVEMENT_SPEED;
        dy = dy * MOVEMENT_SPEED;
        canvas.setTranslateX(canvas.getTranslateX() + dx);
        canvas.setTranslateY(canvas.getTranslateY() + dy);
    }

    public void calculateHit(double hit) {
        health -= hit;
        if (health <= 0) {
            Handler.death(this);
        }
    }

    public void setCanvas(Canvas canvas) { this.canvas = canvas; }

    public Canvas getCanvas() { return canvas; }
    public UUID getUuid() { return uuid; }

}
