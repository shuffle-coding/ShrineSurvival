package erik.wiesi.model.entities;

import erik.wiesi.main.handler.Handler;
import javafx.scene.canvas.Canvas;

import java.util.UUID;

public abstract class Entity {

    private Canvas canvas;
    private final UUID uuid;
    private float health;                  // Initialized in Child Constructor
    private float movementSpeed = 5;      // Default MOVEMENT_SPEED
    private int[] disallowed;

    public Entity () {
        this.uuid = UUID.randomUUID();
        disallowed = new int[] {0,0};
    }

    public void setCanvas(Canvas canvas) { this.canvas = canvas; }
    public void setDisallowed(int[] disallowed) {
        this.disallowed = new int[] {disallowed[0], disallowed[1]};
    }
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
    public void setHealth(float health) {
        this.health = health;
    }
    public Canvas getCanvas() { return canvas; }
    public UUID getUuid() { return uuid; }
    public float getMovementSpeed() { return movementSpeed; }
    public int[] getDisallowed() {
        return disallowed;
    }

}
