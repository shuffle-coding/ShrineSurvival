package erik.wiesi.model;

import javafx.scene.canvas.Canvas;

import java.util.UUID;

public class Entity {


    private Canvas canvas;
    private double posX;
    private double posY;
    private final UUID uuid;

    public Entity (Canvas canvas, double posX, double posY) {
        this.canvas = canvas;
        this.posX = posX;
        this.posY = posY;
        this.uuid = UUID.randomUUID();
    }

    public void setCanvas(Canvas canvas) { this.canvas = canvas; }

    public Canvas getCanvas() { return canvas; }
    public double getPosX() { return posX; }
    public double getPosY() { return posY; }
    public UUID getUuid() { return uuid; }


}
