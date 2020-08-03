package erik.wiesi.model.entities;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {

    private Canvas canvas;
    private final UUID uuid;
    private int health;                  // Initialized in Child Constructor
    private float movementSpeed = 5;      // Default MOVEMENT_SPEED
    private int[] disallowed;
    private ImageView weapon;
    private int weaponDamage = 10;
    private long latestDamage;

    public Entity () {
        this.uuid = UUID.randomUUID();
        disallowed = new int[] {0,0};
        weapon = new ImageView(new Image(getClass().getResource("/SpriteSheets/SingleSprites/sword.png").toString()));
        latestDamage = System.currentTimeMillis();
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    public void setDisallowed(int[] disallowed) {
        this.disallowed = new int[] {disallowed[0], disallowed[1]};
    }
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
    public void setHealth(int health) {
        latestDamage = System.currentTimeMillis();
        this.health = health;
    }
    public void setWeapon(ImageView weapon) {
        this.weapon = weapon;
    }
    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }
    public Canvas getCanvas() {
        return canvas;
    }
    public UUID getUuid() {
        return uuid;
    }
    public float getMovementSpeed() {
        return movementSpeed;
    }
    public int getHealth() {
        return health;
    }
    public int[] getDisallowed() {
        return disallowed;
    }
    public ImageView getWeapon() {
        return weapon;
    }
    public int getWeaponDamage() {
        return weaponDamage;
    }
    public long getLatestDamage() {
        return latestDamage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return getUuid().equals(entity.getUuid());
    }
}
