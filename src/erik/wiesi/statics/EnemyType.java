package erik.wiesi.statics;

/**
 * EnemyTypes Predefined for later usage
 */
public enum EnemyType {

    /**
     * Zombie Enemy
     */
    ZOMBIE(50, 1.5),
    /**
     * Orc Enemy
     */
    ORC(70, 2),
    /**
     * Goblin Enemy
     */
    GOBLIN(30, 1.7),
    /**
     * Bandit Enemy
     */
    BANDIT(100, 2.5);

    private final int HEALTH;
    private final float MOVEMENT_SPEED;

    EnemyType(int health, double movementSpeed) {
        this.HEALTH = health;
        this.MOVEMENT_SPEED = (float) movementSpeed;
    }

    /**
     * @return returns this EnemyType's Health
     */
    public int getHEALTH() { return HEALTH; }

    /**
     * @return returns this EnemyType's Movement Speed
     */
    public double getMOVEMENT_SPEED() { return MOVEMENT_SPEED; }
}
