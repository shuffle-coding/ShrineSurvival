package erik.wiesi.sprites;

import javafx.scene.canvas.Canvas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TileMap {

    private Map<Integer[], Integer[]> tileMap;
    private List<Integer[]> spriteList;
    private Spritesheet spritesheet;
    private Sprite sprite;
    private int rescaleFactor = 1;

    private int sizeX = 1600;
    private int sizeY;


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

        spriteList.forEach(i -> System.out.println(i[0] + " + " + i[1]));

        sprite = new Sprite(spritesheet, sizeX, sizeY);
        tileMap.forEach((key, value) -> sprite.addSprite(value[0], value[1], key[0], key[1]));

    }

    public Map<Integer[], Integer[]> getTileMap() {
        return tileMap;
    }

    public Canvas getSprite() {
        return sprite.getCanvas();
    }

}
