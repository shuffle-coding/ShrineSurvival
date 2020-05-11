package erik.wiesi.view.playSolo;

import erik.wiesi.model.TileMap;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class PlaySoloScene {

    private AnchorPane mainPane;
    private TileMap tileMap;

    private final String SPRITESHEET = "/SpriteSheets/roguelikeSheet_transparent.png";
    private List<Integer[]> sprites;

    public PlaySoloScene(AnchorPane mainPane) {

        this.mainPane = mainPane;

        sprites = new ArrayList<>();
        Integer[] tempInt = new Integer[] {3, 7};
        sprites.add(tempInt);

        Integer[] tempInt1 = new Integer[] {3, 10};
        sprites.add(tempInt1);
        Integer[] tempInt2 = new Integer[] {3, 16};
        sprites.add(tempInt2);

        tileMap = new TileMap(SPRITESHEET, sprites);

        mainPane.getChildren().add(tileMap.getCanvas());
        tileMap.getCanvas().setLayoutX(0);
        tileMap.getCanvas().setLayoutY(0);

    }
}
