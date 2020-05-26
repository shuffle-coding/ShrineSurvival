package erik.wiesi.statics;

public enum EnemyType {

    ZOMBIE(50, 1.5),
    ORC(70, 2),
    GOBLIN(30, 1.7),
    BANDIT(100, 2.5);

    private final double HEALTH;
    private final double MOVEMENT_SPEED;

    private EnemyType(double health, double movementSpeed) {
        this.HEALTH = health;
        this.MOVEMENT_SPEED = movementSpeed;
    }

    public double getHEALTH() { return HEALTH; }
    public double getMOVEMENT_SPEED() { return MOVEMENT_SPEED; }
}
