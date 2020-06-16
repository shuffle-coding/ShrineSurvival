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

        if (dx != 0 && dy != 0) {
            double relative = Math.sqrt(MOVEMENT_SPEED * MOVEMENT_SPEED / 2);
            dx = dx * relative;
            dy = dy * relative;
        } else {
            dx = dx * MOVEMENT_SPEED;
            dy = dy * MOVEMENT_SPEED;
        }

        canvas.setTranslateX(canvas.getTranslateX() + dx);
        canvas.setTranslateY(canvas.getTranslateY() + dy);
    }

    public void movement(int dx, int dy, Entity other) {
        int[] disallowed = collision(other);
        if (dx == disallowed[0]) dx = 0;
        if (dy == disallowed[1]) dy = 0;
        movement(dx, dy);
    }

    public void calculateHit(double hit) {
        health -= hit;
        if (health <= 0) {
            Handler.death(this);
        }
    }

    public int[] collision(Entity other) {

        double myCanvasX = canvas.getTranslateX();
        double myCanvasY = canvas.getTranslateY();
        double otherCanvasX = other.getCanvas().getTranslateX();
        double otherCanvasY = other.getCanvas().getTranslateY();
        double sizeX = (canvas.getWidth() * canvas.getScaleX() / 2) + (other.getCanvas().getWidth() * other.getCanvas().getScaleX() / 2);
        double sizeY = (canvas.getHeight() * canvas.getScaleY() / 2) + (other.getCanvas().getHeight() * other.getCanvas().getScaleY() / 2);

        if (myCanvasX + sizeX <= otherCanvasX && myCanvasY + sizeY <= otherCanvasY) {
            return new int[] {1, 1};
        } else if (myCanvasX - sizeX <= otherCanvasX && myCanvasY - sizeY <= otherCanvasY) {
            return new int[] {-1, -1};
        } else if (myCanvasX + sizeX <= otherCanvasX && myCanvasY - sizeY <= otherCanvasY) {
            return new int[] {1, -1};
        } else if (myCanvasX - sizeX <= otherCanvasX && myCanvasY + sizeY <= otherCanvasY) {
            return new int[] {-1, 1};
        } else {
            return new int[] {0, 0};
        }
    }

//    public boolean collision(Entity entity) {
//        return canvas.getBoundsInParent().intersects(entity.getCanvas().getBoundsInParent());
//    }

    public void setCanvas(Canvas canvas) { this.canvas = canvas; }

    public Canvas getCanvas() { return canvas; }
    public UUID getUuid() { return uuid; }

}
