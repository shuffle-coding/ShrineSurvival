package erik.wiesi.model.entities;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {

    private Canvas canvas;
    private final UUID uuid;
    private float health;                  // Initialized in Child Constructor
    private float movementSpeed = 5;      // Default MOVEMENT_SPEED
    private int[] disallowed;
    private ImageView weapon;
    private float weaponDamage = 10;

    public Entity () {
        this.uuid = UUID.randomUUID();
        disallowed = new int[] {0,0};
        weapon = new ImageView(new Image(getClass().getResource("/SpriteSheets/SingleSprites/sword.png").toString()));
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
    public void setHealth(float health) {
        this.health = health;
    }
    public void setWeapon(ImageView weapon) {
        this.weapon = weapon;
    }
    public void setWeaponDamage(float weaponDamage) {
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
    public float getHealth() {
        return health;
    }
    public int[] getDisallowed() {
        return disallowed;
    }
    public ImageView getWeapon() {
        return weapon;
    }
    public float getWeaponDamage() {
        return weaponDamage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return getUuid().equals(entity.getUuid());
    }
}
