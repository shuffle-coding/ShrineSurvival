package erik.wiesi.main.handler;

import erik.wiesi.model.entities.Enemy;
import erik.wiesi.model.entities.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Handler {

    private static int round = 0;
    private static List<Enemy> enemies;

    public static void startRound() {
        round++;

    }

    public static void movement(Entity entityMe, float dx, float dy) {

        if (dx != 0 && dy != 0) {
            float relative = (float) Math.sqrt(entityMe.getMovementSpeed() * entityMe.getMovementSpeed() / 2);
            dx = dx * relative;
            dy = dy * relative;
        } else {
            dx = dx * entityMe.getMovementSpeed();
            dy = dy * entityMe.getMovementSpeed();
        }

        entityMe.getCanvas().setTranslateX(entityMe.getCanvas().getTranslateX() + dx);
        entityMe.getCanvas().setTranslateY(entityMe.getCanvas().getTranslateY() + dy);
    }

    public static void movement(Entity entityMe, int dx, int dy, Entity other) {
        int[] disallowed = new int[] {0, 0};
        if (!Arrays.equals(entityMe.getDisallowed(), new int[] {0, 0})) {
            return;
        } else if (collisionDetect(entityMe, other)) {
            disallowed = collisionDirection(entityMe, other);
        }
        if (dx == disallowed[0]) dx = 0;
        if (dy == disallowed[1]) dy = 0;
        movement(entityMe, dx, dy);
    }

    public static boolean collisionDetect (Entity a, Entity b) {
        if (a.getCanvas().getBoundsInParent().intersects(b.getCanvas().getBoundsInParent())) {
            return true;
        }
        return false;
    }

    public static int[] collisionDirection (Entity entityMe, Entity entityOther) {
        double sizeAX = entityMe.getCanvas().getWidth() * entityMe.getCanvas().getScaleX();
        double sizeAY = entityMe.getCanvas().getHeight() * entityMe.getCanvas().getScaleY();
        double sizeBX = entityOther.getCanvas().getWidth() * entityOther.getCanvas().getScaleX();
        double sizeBY = entityOther.getCanvas().getHeight() * entityOther.getCanvas().getScaleY();
        double sizeX = (sizeAX + sizeBX + 10) / 2;
        double sizeY = (sizeAY * sizeBY + 10) / 2;
        double posAX = entityMe.getCanvas().getTranslateX() + (sizeAX / 2);
        double posAY = entityMe.getCanvas().getTranslateY() + (sizeAY / 2);
        double posBX = entityOther.getCanvas().getTranslateX() + (sizeBX / 2);
        double posBY = entityOther.getCanvas().getTranslateY() + (sizeBY / 2);
        double deltaX = posAX - posBX;
        double deltaY = posAY - posBY;
        int[] result = new int[2];

        if (deltaX > 0 && deltaX < sizeX){
            result[0] = -1;
            if (deltaY > 0 && deltaY < sizeY) { result[1] = -1;}
            else if (deltaY < 0 && deltaY * -1 < sizeY) { result[1] = 1; }
            else {result[1] = 0;}
            return result;
        } else if (deltaX < 0 && deltaX * -1 < sizeX) {
            result[0] = 1;
            if (deltaY > 0 && deltaY < sizeY) { result[1] = -1;}
            else if (deltaY < 0 && deltaY * -1 < sizeY) { result[1] = 1; }
            else {result[1] = 0;}
            return result;
        } else { return new int[] {0, 0}; }
     }
}
