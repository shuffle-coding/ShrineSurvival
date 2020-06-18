package erik.wiesi.model.entities;

import erik.wiesi.main.handler.Handler;
import javafx.scene.canvas.Canvas;

import java.util.UUID;

public abstract class Entity {

    Canvas canvas;
    private final UUID uuid;
    float health;                  // Initialized in Child Constructor
    float MovementSpeed = 5;      // Default MOVEMENT_SPEED

    public Entity () {
        this.uuid = UUID.randomUUID();
    }


//    public void calculateHit(float hit) {
//        health -= hit;
//        if (health <= 0) {
//            Handler.death(this);
//        }
//    }

    public void setCanvas(Canvas canvas) { this.canvas = canvas; }

    public Canvas getCanvas() { return canvas; }
    public UUID getUuid() { return uuid; }
    public float getMovementSpeed() { return MovementSpeed; }

}
