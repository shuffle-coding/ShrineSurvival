package erik.wiesi.model;

import erik.wiesi.view.ViewManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TileMap {

    private Map<Integer[], Integer[]> tileMap;
    private List<Integer[]> spriteList;
    private Spritesheet spritesheet;

    private final double sizeX = ViewManager.getWIDTH();
    private final double sizeY = ViewManager.getHEIGHT();

    private int totalX;
    private int totalY;

    public TileMap(String spritesheetUrl, List<Integer[]> spriteList) {

        this.spriteList = spriteList;

        spritesheet = new Spritesheet(spritesheetUrl);
        int tileSize = (int) spritesheet.getTilesize();
        int totalX = (int) sizeX / tileSize;
        int totalY = (int) sizeY / tileSize;

        tileMap = new HashMap<>();

        Random random = new Random(spriteList.size());
        Integer[] tempKey = new Integer[2];
        for (int y= 0; y < totalY ; y++) {
            for (int x = 0; x < totalX; x++) {
                int r = random.nextInt();
                tempKey[0] = x;
                tempKey[1] = y;
                tileMap.put(tempKey, spriteList.get(r));
            }
        }
    }

    public Map<Integer[], Integer[]> getTileMap() {
        return tileMap;
    }

}
