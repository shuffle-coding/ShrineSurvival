package erik.wiesi.view.playSolo.handler;

import erik.wiesi.model.entities.Enemy;
import erik.wiesi.model.entities.Entity;
import erik.wiesi.model.entities.Player;
import erik.wiesi.sprites.PlayerSprite;
import erik.wiesi.view.playSolo.PlaySoloScene;
import erik.wiesi.view.playSolo.Score;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Handler {

    private static List<Entity> entities;
    private static Player player;
    private static AnchorPane mainPane;

    /**
     * Refreshes the List of Entities inside the Handler
     * @param newEntities List of all Entities
     */
    public static void setEntities(List<Entity> newEntities) {
        entities = newEntities;
    }

    /**
     * @return returns List of all Entities
     */
    public static List<Entity> getEntities() {
        return entities;
    }

    /**
     * Player has to be set to check if Entity is Player or not
     * @param playerChar sets Player
     */
    public static void setPlayer(Player playerChar) {
        player = playerChar;
    }

    /**
     * @param pane sets the AnchorPane as mainPane to be drawn at
     */
    public static void setMainPane(AnchorPane pane) {
        mainPane = pane;
    }

    /**
     * This method lets you move an Entity in a specific direction
     * @param me entity to be moved
     * @param dx X direction to be moved (+1 is Right / -1 is Left) with full Speed
     * @param dy Y direction to be moved (+1 is Down / -1 is Up) with full Speed
     */
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

    /**
     * This method lets you move an Entity towards another Entity
     * @param me Entity to be moved
     * @param other Entity to be moved towards at
     */
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

    /**
     * Checks if Direction to be moved is clear
     * returns locked Directions as boolean Array[X, Y]
     * @param me Entity that moves
     * @param dx X direction to be moved (+1 is Right / -1 is Left) with full Speed
     * @param dy Y direction to be moved (+1 is Down / -1 is Up) with full Speed
     * @return return boolean[X, Y] if direction shall be locked or not
     */
    private static boolean[] directionalCheck(Entity me, int dx, int dy) {
        Bounds myBounds = me.getCanvas().getBoundsInParent();
        float mySafetyBounds = (float) (me.getMovementSpeed() * 3.5);
        boolean[] result = new boolean[]{false, false};

        entities.stream().filter(entity -> !entity.equals(me)).forEach(other -> {
            Bounds otherBounds = other.getCanvas().getBoundsInParent();
            if (myBounds.intersects(otherBounds)) {
                if (other.equals(player)) {
                    dealDamage(me);
                }
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

    /**
     * This method gets the Weapon from the Entity class and attack in a specific direction depending on the
     * 2 directional parameters
     * @param me Reference Entity, from this Entity will be drawn
     * @param attackX X direction to be attacked at relatively to the Entity (+1 is Right / -1 is Left)
     * @param attackY Y direction to be attacked at relatively to the Entity (+1 is Down / -1 is Up)
     * @param shownTime Duration in Milliseconds for the Weapon to be shown
     */
    public static void drawWeapon(Entity me, int attackX, int attackY, int shownTime) {
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
    }

    /**
     * Removes the Weapon from the mainPane
     * @param me Entity of the Weapon to be removed
     */
    public static void removeWeapon(Entity me) {
        try {
            mainPane.getChildren().remove(me.getWeapon());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates Damage and score
     * @param me Entity that attacks
     * @param score Score of the Game
     */
    public static void attack(Entity me, Score score) {
        Bounds weapon = me.getWeapon().getBoundsInParent();

        entities = entities.stream().peek(entity -> {
            if (!entity.equals(me) && weapon.intersects(entity.getCanvas().getBoundsInParent())) {
                dealDamage(me, entity, score);
            }
        }).filter(e -> e.getHealth() > 0).collect(Collectors.toList());
    }

    private static void dealDamage(Entity attacker, Entity defender, Score score) {
        if ((System.currentTimeMillis() - defender.getLatestDamage()) >= 200) {
            defender.setHealth(defender.getHealth() - attacker.getWeaponDamage());
            if (defender.getHealth() <= 0) {
                mainPane.getChildren().remove(defender.getCanvas());
                if (defender instanceof Enemy) {
                    score.addScore(((Enemy) defender).getValue());
                }
            }
        }
    }
    private static void dealDamage(Entity attacker) {
        if ((System.currentTimeMillis() - player.getLatestDamage()) >= 1000) {
            player.setHealth(player.getHealth() - attacker.getWeaponDamage());
            if (player.getHealth() <= 0) {
                PlaySoloScene.gameOver();
            }
        }
    }

    /**
     * Sends PlayerSprite and Score to the Database
     * @param playerSprite PlayerSprite used in Game
     * @param score Score at end of Game
     */
    public static void sendData(PlayerSprite playerSprite, Score score) throws SQLException {
        Connection con = new DatabaseConnection().getConnection();

        int[] body = playerSprite.getBodyModel();
        int[] pants = playerSprite.getPants();
        int[] shoes = playerSprite.getShoes();
        int[] top = playerSprite.getTop();
        int[] head = playerSprite.getHead();


        String sql = ("SELECT player_model_id FROM player_model WHERE " +
                "body_x = %d AND body_y = %d AND " +
                "pants_x = %d AND pants_y = %d AND " +
                "shoes_x = %d AND shoes_y = %d AND " +
                "top_x = %d AND top_y = %d AND " +
                "head_x = %d AND head_y = %d;")
                .formatted(body[0], body[1], pants[0], pants[1], shoes[0], shoes[1], top[0], top[1], head[0], head[1]);

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.execute();
        ResultSet result = stmt.getResultSet();
        if (!result.next()) {
            sql = ("INSERT INTO player_model(body_x, body_y, pants_x, pants_y, shoes_x, shoes_y, top_x, top_y, head_x, head_y)VALUES(%d, %d, %d, %d, %d, %d, %d, %d, %d, %d);")
                    .formatted(body[0], body[1], pants[0], pants[1], shoes[0], shoes[1], top[0], top[1], head[0], head[1]);
            stmt = con.prepareStatement(sql);
            stmt.execute();

            sql = ("SELECT player_model_id FROM player_model WHERE " +
                    "body_x = %d AND body_y = %d AND " +
                    "pants_x = %d AND pants_y = %d AND " +
                    "shoes_x = %d AND shoes_y = %d AND " +
                    "top_x = %d AND top_y = %d AND " +
                    "head_x = %d AND head_y = %d;")
                    .formatted(body[0], body[1], pants[0], pants[1], shoes[0], shoes[1], top[0], top[1], head[0], head[1]);
            stmt = con.prepareStatement(sql);
            stmt.execute();
            result = stmt.getResultSet();
            result.next();
        }
        int modelId = result.getInt("player_model_id");

        sql = "INSERT INTO score(player_model_id, player_name, score, play_time, defeated_enemies, waves) VALUES (%d , '%s', %d, '%s', %d, %d);"
                .formatted(modelId, score.getName(), score.getScore(), score.getPlayTime(), score.getDefeatedEnemies(), score.getWaves());
        stmt = con.prepareStatement(sql);
        stmt.execute();

        result.close();
        stmt.close();
        con.close();
    }
}
