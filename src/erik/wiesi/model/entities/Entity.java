package erik.wiesi.model.entities;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.UUID;

public abstract class Entity {

    private Canvas canvas;
    private final UUID uuid;
    private int health;                  // Initialized in Child Constructor
    private float movementSpeed = 5;      // Default MOVEMENT_SPEED
    private final int[] disallowed;
    private final ImageView weapon;
    private final int weaponDamage = 10;
    private long latestDamage;

    /**
     * Generates Default Entity for Enemy or Player
     */
    public Entity () {
        this.uuid = UUID.randomUUID();
        disallowed = new int[] {0,0};
        weapon = new ImageView(new Image(getClass().getResource("/SpriteSheets/SingleSprites/sword.png").toString()));
        latestDamage = System.currentTimeMillis();
    }

    /**
     * Sets the Canvas for Enemy or Player to be drawn
     * @param canvas sets Canvas for this Entity
     */
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Overrides Default movementSpeed Value
     * @param movementSpeed Sets Movement Speed if not Default
     */
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * Overrides Default Health Value
     * Registers Timestamp for the latest Damage taken by this Entity
     * @param health Sets Health if not Default
     */
    public void setHealth(int health) {
        latestDamage = System.currentTimeMillis();
        this.health = health;
    }

//    public void setWeapon(ImageView weapon) {
//        this.weapon = weapon;
//    }
//    public void setWeaponDamage(int weaponDamage) {
//        this.weaponDamage = weaponDamage;
//    }

    /**
     * @return returns Canvas for this Entity
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * @return returns UUID for this Entity
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return returns movementSpeed for this Entity
     */
    public float getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * @return returns Health for this Entity
     */
    public int getHealth() {
        return health;
    }
//    public int[] getDisallowed() {
//        return disallowed;
//    }

    /**
     * Returns ImageView of this Entity's Weapon for drawing on Pane
     * @return returns ImageView of this Entity's Weapon
     */
    public ImageView getWeapon() {
        return weapon;
    }

    /**
     * @return returns attack Value for this Entity
     */
    public int getWeaponDamage() {
        return weaponDamage;
    }

    /**
     * Timestamp is given in Milliseconds
     * @return returns Timestamp for the last Damage taken by this Entity
     */
    public long getLatestDamage() {
        return latestDamage;
    }

    /**
     * Comparator for Object Class and UUID
     * returns true if both are equal
     * @param o Comparator for Equals Method
     * @return returns true if Object Class and UUID is equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return getUuid().equals(entity.getUuid());
    }
}
