package erik.wiesi.sprites;

import javafx.scene.canvas.Canvas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class TileMap {

    private final Map<Integer[], Integer[]> tileMap;
    private final List<Integer[]> spriteList;
    private final Spritesheet spritesheet;
    private final Sprite sprite;
    private int rescaleFactor = 1;

    private int sizeX = 1600;
    private int sizeY;


    /**
     * Creats new {@link TileMap} based on Stage Resolution of 1600x900
     * Creats a single Sprite with CanvasSize of the this Resolution and adds new Tiles as new Sprites to this Canvas
     * Rescale able with rescaleFactor parameter
     * @param spritesheetUrl String for {@link Spritesheet} used for creating this {@link TileMap}
     * @param spriteList List of Integer[] for X and Y counters on this {@link Spritesheet}
     * @param rescaleFactor defines a scaling for this {@link TileMap}
     */
    public TileMap(String spritesheetUrl, List<Integer[]> spriteList, int rescaleFactor) {

        this.spriteList = spriteList;
        this.rescaleFactor = rescaleFactor;
        sizeX = sizeX / rescaleFactor;
        sizeY = sizeX * 9 / 16;

        spritesheet = new Spritesheet(spritesheetUrl);
        int tileSize = (int) spritesheet.getTilesize();
        int totalX = sizeX / tileSize;
        int totalY = sizeY / tileSize;

        tileMap = new HashMap<>();

        for (int y= 0; y < totalY ; y++) {
            for (int x = 0; x < totalX; x++) {
                int random = ThreadLocalRandom.current().nextInt(10);
                if (random >= 2) random = 2;
                tileMap.put(new Integer[] {x, y}, this.spriteList.get(random));
            }

        }

        sprite = new Sprite(spritesheet, sizeX, sizeY);
        tileMap.forEach((key, value) -> sprite.addSprite(value[0], value[1], key[0], key[1]));

        sprite.setScale(rescaleFactor);

    }

    /**
     * Creats new {@link TileMap} based on Stage Resolution of 1600x900
     * Creats a single Sprite with CanvasSize of the this Resolution and adds new Tiles as new Sprites to this Canvas
     * @param spritesheetUrl String for {@link Spritesheet} used for creating this {@link TileMap}
     * @param spriteList List of Integer[] for X and Y counters on this {@link Spritesheet}
     */
    public TileMap(String spritesheetUrl, List<Integer[]> spriteList) {

        this.spriteList = spriteList;
        sizeY = sizeX * 9 / 16;

        spritesheet = new Spritesheet(spritesheetUrl);
        int tileSize = (int) spritesheet.getTilesize();
        int totalX = sizeX / tileSize;
        int totalY = sizeY / tileSize;

        tileMap = new HashMap<>();

        for (int y= 0; y < totalY ; y++) {
            for (int x = 0; x < totalX; x++) {
                int random = ThreadLocalRandom.current().nextInt(10);
                if (random >= 2) random = 2;
                tileMap.put(new Integer[] {x, y}, this.spriteList.get(random));
            }

        }

        sprite = new Sprite(spritesheet, sizeX, sizeY);
        tileMap.forEach((key, value) -> sprite.addSprite(value[0], value[1], key[0], key[1]));

    }

    /**
     * @return returns this Map's Canvas
     */
    public Canvas getCanvas() {
        return sprite.getCanvas();
    }

}
