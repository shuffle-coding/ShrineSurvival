package erik.wiesi.model.entities;

import javafx.scene.canvas.Canvas;


public class Player extends Entity {


    /**
     * Constructs new Player Entity with given Body Parts from PlayerSprite
     * @param canvas Requires Canvas given by PlayerSprite Class
     */
    public Player(Canvas canvas) {
        super();
        this.setCanvas(canvas);
        this.setHealth(100);
    }

    /**
     * @return returns Name
     */
    public String getName() {
        return name;
    }
}
