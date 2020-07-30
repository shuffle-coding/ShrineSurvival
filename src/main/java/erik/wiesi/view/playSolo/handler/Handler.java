package erik.wiesi.view.playSolo.handler;

import erik.wiesi.model.entities.Entity;
import erik.wiesi.model.entities.Player;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Handler {

    private static List<Entity> entities;
    private static Player player;
    private static AnchorPane mainPane;

    public static void setEntities(List<Entity> newEntities) {
        entities = newEntities;
    }
    public static List<Entity> getEntities() {
        return entities;
    }
    public static void setPlayer(Player playerChar) {
        player = playerChar;
    }
    public static void setMainPane(AnchorPane pane) {
        mainPane = pane;
    }
    public static void movement(Entity me, int dx, int dy) {
        boolean[] disallowed = directionalCheck(me, dx, dy);
        if (disallowed[0]) {
            dx = 0;
        }
        if (disallowed[1]) {
            dy = 0;
        }

        float speedX;
        float speedY;
        if (dx != 0 && dy != 0) {
            float relative = (float) Math.sqrt(me.getMovementSpeed() * me.getMovementSpeed() / 2);
            speedX = dx * relative;
            speedY = dy * relative;
        } else {
            speedX = dx * me.getMovementSpeed();
            speedY = dy * me.getMovementSpeed();
        }

        me.getCanvas().setTranslateX(me.getCanvas().getTranslateX() + speedX);
        me.getCanvas().setTranslateY(me.getCanvas().getTranslateY() + speedY);
    }

    public static void movement(Entity me, Entity other) {

        double otherPosX = other.getCanvas().getBoundsInParent().getCenterX();
        double otherPosY = other.getCanvas().getBoundsInParent().getCenterY();
        int dx = 0, dy = 0;
        double enemyPosX = me.getCanvas().getBoundsInParent().getCenterX();
        double enemyPosY = me.getCanvas().getBoundsInParent().getCenterY();
        if (enemyPosX < otherPosX) dx += 1;
        if (enemyPosX > otherPosX) dx -= 1;
        if (enemyPosY < otherPosY) dy += 1;
        if (enemyPosY > otherPosY) dy -= 1;

        movement(me, dx, dy);
    }

    private static boolean[] directionalCheck(Entity me, int dx, int dy) {
        Bounds myBounds = me.getCanvas().getBoundsInParent();
        float mySafetyBounds = (float) (me.getMovementSpeed() * 3.5);
        boolean[] result = new boolean[] {false, false};

        entities.stream().filter(entity -> entity.getUuid() != me.getUuid()).forEach(other -> {
            Bounds otherBounds = other.getCanvas().getBoundsInParent();
            if (myBounds.intersects(otherBounds)) {
                if (!result[0]) {
                if (dx == 1 && myBounds.getMaxX() - mySafetyBounds <= otherBounds.getMinX()) {
                        result[0] = true;
                    } else if (dx == -1 && myBounds.getMinX() + mySafetyBounds >= otherBounds.getMaxX()) {
                        result[0] = true;
                    }
                }
                if (!result[1]) {
                    if (dy == 1 && myBounds.getMaxY() - mySafetyBounds <= otherBounds.getMinY()) {
                        result[1] = true;
                    } else if (dy == -1 && myBounds.getMinY() + mySafetyBounds >= otherBounds.getMaxY()) {
                        result[1] = true;
                    }
                }
            }
        });
        return result;
    }

    public static PathTransition drawWeapon(Entity me, int attackX, int attackY, int shownTime) {
        ImageView weapon = me.getWeapon();
        int radius = 60;

        int angleStart = 0;
        if (attackX == 1 && attackY == -1) {
            angleStart = 45;
        } else if (attackX == 1 && attackY == 0) {
            angleStart = 90;
        } else if (attackX == 1 && attackY == 1) {
            angleStart = 135;
        } else if (attackX == 0 && attackY == 1) {
            angleStart = 180;
        } else if (attackX == -1 && attackY == 1) {
            angleStart = 225;
        } else if (attackX == -1 && attackY == 0) {
            angleStart = 270;
        } else if (attackX == -1 && attackY == -1) {
            angleStart = 315;
        }
        angleStart += 30;
        int angleEnd = angleStart - 60;

        Arc path = new Arc(me.getCanvas().getBoundsInParent().getCenterX(), me.getCanvas().getBoundsInParent().getCenterY(), radius, radius, -angleStart + 90, 60);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(shownTime));
        pathTransition.setPath(path);
        pathTransition.setNode(weapon);
        pathTransition.setCycleCount(1);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(shownTime));
        rotateTransition.setNode(weapon);
        rotateTransition.setFromAngle(angleStart);
        rotateTransition.setToAngle(angleEnd);

        mainPane.getChildren().add(weapon);
        weapon.setScaleX(2);
        weapon.setScaleY(2);
        pathTransition.playFromStart();
        rotateTransition.playFromStart();
        return pathTransition;
    }

    public static void removeWeapon(Entity me) {
        try {
            mainPane.getChildren().remove(me.getWeapon());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void repositionWeapon(Entity me, PathTransition pathTransition) {
        pathTransition.getPath().setTranslateX(me.getCanvas().getBoundsInParent().getCenterX());
        pathTransition.getPath().setTranslateY(me.getCanvas().getBoundsInParent().getCenterY());
    }

    public static void attack(Entity me) {
        Bounds weapon = me.getWeapon().getBoundsInParent();

        entities = entities.stream().peek(entity -> {
            if (!entity.equals(me) && weapon.intersects(entity.getCanvas().getBoundsInParent())) {
                dealDamage(me, entity);
            }
        }).filter(e -> e.getHealth() > 0).collect(Collectors.toList());
    }

    private static void dealDamage(Entity attacker, Entity defender) {
        if ((System.currentTimeMillis() - defender.getLatestDamage()) >= 200) {
            defender.setHealth(defender.getHealth() - attacker.getWeaponDamage());
            if (defender.getHealth() <= 0) {
                mainPane.getChildren().remove(defender.getCanvas());
            }
        }
    }
}
