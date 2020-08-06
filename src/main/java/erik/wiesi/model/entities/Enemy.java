package erik.wiesi.model.entities;

import erik.wiesi.sprites.Sprite;
import erik.wiesi.sprites.Spritesheet;
import erik.wiesi.statics.EnemyType;
import javafx.scene.canvas.Canvas;

public class Enemy extends Entity{

    private int value;

    /**
     * Returns Score of the this Enemy
     * @return returns Score Value of this Enemy
     */
    public int getValue() {
        return value;
    }

    /**
     * Constructs new Enemy with Default Values
     */
    public Enemy() {
        super();
        Spritesheet spritesheet = new Spritesheet("/SpriteSheets/roguelikeChar_transparent.png");
        Sprite sprite = new Sprite(spritesheet);
        sprite.addSprite(1, 11);
        this.setCanvas(sprite.getCanvas());
        this.setMovementSpeed((float) EnemyType.ZOMBIE.getMOVEMENT_SPEED());
        this.setHealth(EnemyType.ZOMBIE.getHEALTH());
        value = (int) (200 - (getHealth() / getMovementSpeed()));
    }

    /**
     * Generates new custom Enemy with given Canvas and EnemyType like "ZOMBIE, ORC, ..."
     * @param canvas sets Canvas for this Enemy
     * @param enemyType EnemyType for specified Enemy
     */
    public Enemy(Canvas canvas, EnemyType enemyType) {
        super();
        this.setCanvas(canvas);
        this.setMovementSpeed((float) enemyType.getMOVEMENT_SPEED());
        this.setHealth(enemyType.getHEALTH());
    }
}
