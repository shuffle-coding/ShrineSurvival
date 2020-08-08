package erik.wiesi.sprites;

import javafx.scene.image.Image;

public class Spritesheet {

    private final double DEFAULT_TILE_SIZE = 16;
    private final double DEFAULT_MARGIN = 1;

    private final double tilesize;
    private final double margin;
    private final Image image;

    /**
     * Creats new {@link Spritesheet} with default tilesize of 16 and default margin of 1
     * @param url defines URL to the Spritesheet.png from resources
     */
    public Spritesheet(String url) {
        this.tilesize = DEFAULT_TILE_SIZE;
        this.margin = DEFAULT_MARGIN;
        image = new Image(getClass().getResource(url).toString());
    }

    /**
     * Creats new {@link Spritesheet} with specified tilesize
     * default margin is 1
     * @param url defines URL to the Spritesheet.png from resources
     * @param tilesize defines tilesize of this {@link Spritesheet}
     */
    public Spritesheet(String url, int tilesize) {
        this.tilesize = tilesize;
        this.margin = DEFAULT_MARGIN;
        image = new Image(getClass().getResource(url).toString());
    }

    /**
     * Creats new {@link Spritesheet} with all custom Parameters
     * @param url defines URL to the Spritesheet.png from resources
     * @param tilesize defines tilesize of this {@link Spritesheet}
     * @param margin defines margin of this {@link Spritesheet}
     */
    public Spritesheet(String url, int tilesize, int margin) {
        this.tilesize = tilesize;
        this.margin = margin;
        image = new Image(getClass().getResource(url).toString());
    }

    /**
     * @return returns tilesize of this {@link Spritesheet}
     */
    public double getTilesize() {
        return tilesize;
    }

    /**
     * @return returns margin of this {@link Spritesheet}
     */
    public double getMargin() {
        return margin;
    }

    /**
     * @return returns Image of this {@link Spritesheet}
     */
    public Image getImage() {
        return this.image;
    }
}
