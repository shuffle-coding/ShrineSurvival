package erik.wiesi.model.characterBuilder;

public class Creator {

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int x;
    private int y;

    public Creator(Types types) {
        this.minX = types.getMinX();
        this.minY = types.getMinY();
        this.maxX = types.getMaxX();
        this.maxY = types.getMaxY();
        x= minX;
        y= minY;
    }

    public int getX() { return x; }
    public int getY() { return y; }

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
