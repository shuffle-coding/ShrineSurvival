package erik.wiesi.model;

import erik.wiesi.view.ViewManager;
import javafx.scene.canvas.Canvas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TileMap {

    private Map<Integer[], Integer[]> tileMap;
    private List<Integer[]> spriteList;
    private Spritesheet spritesheet;
    private Sprite canvas;

    private final int sizeX = (int) ViewManager.getWIDTH();
    private final int sizeY = (int) ViewManager.getHEIGHT();

    private int totalX;
    private int totalY;

    public TileMap(String spritesheetUrl, List<Integer[]> spriteList) {

        this.spriteList = spriteList;


        spritesheet = new Spritesheet(spritesheetUrl);
        int tileSize = (int) spritesheet.getTilesize();
        int totalX = (int) sizeX / tileSize;
        int totalY = (int) sizeY / tileSize;

//        System.out.println(totalX + " + " + totalY);

        tileMap = new HashMap<>();

        Random random = new Random();

        for (int y= 0; y < totalY ; y++) {
            for (int x = 0; x < totalX; x++) {
                Integer[] tempKey = new Integer[] {x, y};
                tileMap.put(tempKey, this.spriteList.get(random.nextInt(spriteList.size())));
            }

        }

        spriteList.forEach(i -> System.out.println(i[0] + " + " + i[1]));

        canvas = new Sprite(spritesheet, sizeX, sizeY);
        tileMap.forEach((key, value) -> canvas.addSprite(value[0], value[1], key[0], key[1]));

    }

    public Map<Integer[], Integer[]> getTileMap() {
        return tileMap;
    }

    public Canvas getCanvas() {
        return canvas.getCanvas();
    }

}
