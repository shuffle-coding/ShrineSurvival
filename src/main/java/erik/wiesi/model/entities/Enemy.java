package erik.wiesi.model.entities;

import erik.wiesi.sprites.Sprite;
import erik.wiesi.sprites.Spritesheet;
import erik.wiesi.statics.EnemyType;
import javafx.scene.canvas.Canvas;

public class Enemy extends Entity{

    public Enemy() {
        super();
        Spritesheet spritesheet = new Spritesheet("/SpriteSheets/roguelikeChar_transparent.png");
        Sprite sprite = new Sprite(spritesheet);
        sprite.addSprite(1, 11);
        this.setCanvas(sprite.getCanvas());
        this.setMovementSpeed((float) EnemyType.ZOMBIE.getMOVEMENT_SPEED());
        this.setHealth((float) EnemyType.ZOMBIE.getHEALTH());
    }

    public Enemy(Canvas canvas, EnemyType enemyType) {
        super();
        this.setCanvas(canvas);
        this.setMovementSpeed((float) enemyType.getMOVEMENT_SPEED());
        this.setHealth((float) enemyType.getHEALTH());
    }
}
