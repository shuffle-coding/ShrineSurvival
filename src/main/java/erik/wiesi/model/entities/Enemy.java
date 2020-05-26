package erik.wiesi.model.entities;

import erik.wiesi.statics.EnemyType;
import javafx.scene.canvas.Canvas;

public class Enemy extends Entity{

    public Enemy(Canvas canvas, EnemyType enemyType) {
        super();
        this.canvas = canvas;
        this.MOVEMENT_SPEED = enemyType.getMOVEMENT_SPEED();
        this.health = enemyType.getHEALTH();
    }
}
