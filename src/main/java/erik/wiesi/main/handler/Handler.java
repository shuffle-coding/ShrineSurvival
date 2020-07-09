package erik.wiesi.main.handler;

import erik.wiesi.model.entities.Entity;
import erik.wiesi.model.entities.Player;
import javafx.geometry.Bounds;

import java.util.List;

public abstract class Handler {

    private static List<Entity> entities;
    private static Player player;

    public static void setEntities(List<Entity> newEntities) {
        entities = newEntities;
    }
    public static List<Entity> getEntities() {
        return entities;
    }
    public static void setPlayer(Player playerChar) {
        player = playerChar;
    }

    public static void movement(Entity entityMe, int dx, int dy) {
        boolean[] disallowed = directionalCheck(entityMe, dx, dy);
        if (disallowed[0]) {
            dx = 0;
        }
        if (disallowed[1]) {
            dy = 0;
        }

        float speedX;
        float speedY;
        if (dx != 0 && dy != 0) {
            float relative = (float) Math.sqrt(entityMe.getMovementSpeed() * entityMe.getMovementSpeed() / 2);
            speedX = dx * relative;
            speedY = dy * relative;
        } else {
            speedX = dx * entityMe.getMovementSpeed();
            speedY = dy * entityMe.getMovementSpeed();
        }

        entityMe.getCanvas().setTranslateX(entityMe.getCanvas().getTranslateX() + speedX);
        entityMe.getCanvas().setTranslateY(entityMe.getCanvas().getTranslateY() + speedY);
    }

    public static void movement(Entity entityMe, Entity other) {

        double otherPosX = other.getCanvas().getBoundsInParent().getCenterX();
        double otherPosY = other.getCanvas().getBoundsInParent().getCenterY();
        int dx = 0, dy = 0;
        double enemyPosX = entityMe.getCanvas().getBoundsInParent().getCenterX();
        double enemyPosY = entityMe.getCanvas().getBoundsInParent().getCenterY();
        if (enemyPosX < otherPosX) dx += 1;
        if (enemyPosX > otherPosX) dx -= 1;
        if (enemyPosY < otherPosY) dy += 1;
        if (enemyPosY > otherPosY) dy -= 1;

        movement(entityMe, dx, dy);
    }

    private static boolean[] directionalCheck(Entity me, int dx, int dy) {
        Bounds myBounds = me.getCanvas().getBoundsInParent();
        boolean[] result = new boolean[] {false, false};

        entities.stream().filter(entity -> entity.getUuid() != me.getUuid()).forEach(other -> {
            Bounds otherBounds = other.getCanvas().getBoundsInParent();
            if (myBounds.intersects(otherBounds)) {
                if (!result[0]) {
                if (dx == 1 && myBounds.getMaxX() - 3 <= otherBounds.getMinX()) {
                        result[0] = true;
                    } else if (dx == -1 && myBounds.getMinX() + 3 >= otherBounds.getMaxX()) {
                        result[0] = true;
                    }
                }
                if (!result[1]) {
                    if (dy == 1 && myBounds.getMaxY() - 3 <= otherBounds.getMinY()) {
                        result[1] = true;
                    } else if (dy == -1 && myBounds.getMinY() + 3 >= otherBounds.getMaxY()) {
                        result[1] = true;
                    }
                }
            }
        });
        return result;
    }

}
