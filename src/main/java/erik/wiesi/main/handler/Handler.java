package erik.wiesi.main.handler;

import erik.wiesi.model.entities.Enemy;
import erik.wiesi.model.entities.Entity;
import erik.wiesi.sprites.Sprite;
import erik.wiesi.sprites.Spritesheet;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

public abstract class Handler {

    private static int round = 0;
    private static List<Enemy> enemies;

    public static void startRound() {
        round++;

    }


    public static void death(Entity entity) {
        entity = null;
    }

}
