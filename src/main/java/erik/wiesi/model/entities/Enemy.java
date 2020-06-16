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
        canvas = sprite.getCanvas();
        this.MOVEMENT_SPEED = EnemyType.ZOMBIE.getMOVEMENT_SPEED();
        this.health = EnemyType.ZOMBIE.getHEALTH();
    }

    public Enemy(Canvas canvas, EnemyType enemyType) {
        super();
        this.canvas = canvas;
        this.MOVEMENT_SPEED = enemyType.getMOVEMENT_SPEED();
        this.health = enemyType.getHEALTH();
    }
}
