package erik.wiesi.model;

import javafx.scene.image.Image;

public class SpritesheetTest {
    private final double DEFAULT_TILE_SIZE = 16;
    private final double DEFAULT_MARGIN = 1;

    private double tilesize;
    public double getTilesize() {
        return tilesize;
    }

    private double margin;
    public double getMargin() {
        return margin;
    }

    private final Image image;
    public SpritesheetTest(String url) {
        this.tilesize = DEFAULT_TILE_SIZE;
        this.margin = DEFAULT_MARGIN;
        image = new Image(getClass().getResource(url).toString());
    }

    public Image getImage() {
        return this.image;
    }

    public SpritesheetTest(String url, int tilesize) {
        this.tilesize = tilesize;
        this.margin = DEFAULT_MARGIN;
        image = new Image(getClass().getResource(url).toString());
    }

    public SpritesheetTest(String url, int tilesize, int spaceBetweenTiles) {
        this.tilesize = tilesize;
        this.margin = spaceBetweenTiles;
        image = new Image(getClass().getResource(url).toString());
    }

}
