package erik.wiesi.statics;

/**
 * BodyParts for Player Model that can be used based on Spritesheet
 */
public enum Types {

    /**
     * Whole Body Part
     */
    BODY(0, 0, 0+1, 0+2),
    /**
     * Head Part
     */
    HEAD(19, 0, 19+7, 0+7),
    /**
     * Top Part
     */
    TOP(6, 0, 6+11, 0+9),
    /**
     * Pants Part
     */
    PANTS(3, 0, 3+0, 0+3),
    /**
     * Shoes Part
     */
    SHOES(4, 0, 4+0, 0+9);

    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;

    Types(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * @return returns minX Counter for this Body Parts Spritesheet Positions
     */
    public int getMinX() { return minX; }

    /**
     * @return returns minY Counter for this Body Parts Spritesheet Positions
     */
    public int getMinY() { return minY; }

    /**
     * @return returns maxX Counter for this Body Parts Spritesheet Positions
     */
    public int getMaxX() { return maxX; }

    /**
     * @return returns maxY Counter for this Body Parts Spritesheet Positions
     */
    public int getMaxY() { return maxY; }
}
