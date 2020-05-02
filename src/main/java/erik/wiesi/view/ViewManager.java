package erik.wiesi.view;

import erik.wiesi.view.mainMenu.MenuScene;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewManager {

    private static final double WIDTH = 1600;
    private static final double HEIGHT = 1200;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ViewManager() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        new MenuScene(mainPane);
    }

    public Stage getMainStage() {
        return mainStage;
    }


}
