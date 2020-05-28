package erik.wiesi.statics;

public enum Types {

    BODY(0, 0, 0+1, 0+2),
    HEAD(19, 0, 19+7, 0+7),
    TOP(6, 0, 6+11, 0+9),
    PANTS(3, 0, 3+0, 0+3),
    SHOES(4, 0, 4+0, 0+9);
//    CHARCLASS();

    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;

    /**
     * @param minX
     * @param minY
     * @param maxX
     * @param maxY
     */
    private Types(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMinX() { return minX; }
    public int getMinY() { return minY; }
    public int getMaxX() { return maxX; }
    public int getMaxY() { return maxY; }
}
