package erik.wiesi.model.characterBuilder;

import erik.wiesi.statics.Types;

public class Creator {

    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;
    private int x;
    private int y;

    /**
     * Generates Bodypart given by Enum
     * @param types Bodypart Enum
     */
    public Creator(Types types) {
        this.minX = types.getMinX();
        this.minY = types.getMinY();
        this.maxX = types.getMaxX();
        this.maxY = types.getMaxY();
        x= minX;
        y= minY;
    }

    /**
     * Returns the X Value of the singular Sprite on the Spritesheet
     * @return X Value of Spritesheet
     */
    public int getX() { return x; }

    /**
     * Returns the Y Value of the singular Sprite on the Spritesheet
     * @return Y Value of Spritesheet
     */
    public int getY() { return y; }

    /**
     * Returns an int Array of the given X and Y Values on The Spritesheet
     * @return Array of X and Y Value of Spritesheet
     */
    public int[] getXY() { return new int[]{x, y};}

    /**
     * Gets the next X and Y Values of this Type
     * @return returns next Values of X and Y Values of Spritesheet
     */
    public int[] getNext() {

        if (y != maxY) {y++;} else if (x != maxX && y == maxY) {
            x++;
            y = minY;
        } else if (x == maxX && y == maxY) {
            x = minX;
            y = minY;
        }

        int[] res = new int[2];
        res[0] = x;
        res[1] = y;

        return res;
    }
}
