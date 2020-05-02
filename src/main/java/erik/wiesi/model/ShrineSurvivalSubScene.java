package erik.wiesi.model;

import javafx.scene.Parent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ShrineSurvivalSubScene extends SubScene {

    private final String FONT = "/Fonts/kenvector_future.ttf";
    private final String BACKGROUND = "/Background/yellow_panel.png";

    public ShrineSurvivalSubScene() {
        super(new AnchorPane(), 600, 800);
        prefHeight(800);
        prefWidth(600);

        Background background = new Background(new BackgroundImage(new Image(getClass().getResource(BACKGROUND).toString(), 600, 800, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                null
        ));

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(background);
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

}
