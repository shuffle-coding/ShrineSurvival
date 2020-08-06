package erik.wiesi.model.entities;

import javafx.scene.canvas.Canvas;


public class Player extends Entity {

    private final String name;

    /**
     * Constructs new Player Entity with given Body Parts from PlayerSprite and Selected Name by Player
     * @param canvas Requires Canvas given by PlayerSprite Class
     * @param name Name selected by the Player
     */
    public Player(Canvas canvas, String name) {
        super();
        this.setCanvas(canvas);
        this.setHealth(100);
        this.name = name;
    }

    /**
     * @return returns Name
     */
    public String getName() {
        return name;
    }
}
