package erik.wiesi.view;

import erik.wiesi.sprites.PlayerSprite;
import erik.wiesi.view.mainMenu.MenuScene;
import erik.wiesi.view.playSolo.PlaySoloScene;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewManager {

    private static final double WIDTH = 1600;
    private static final double HEIGHT = (WIDTH * 9 / 16) - 4;
    private static AnchorPane mainPane;
    private static Scene mainScene;
    private static Stage mainStage;

    public Stage getMainStage() {
        return mainStage;
    }

    public static double getWIDTH() { return WIDTH; }

    public static double getHEIGHT() { return HEIGHT; }

    public AnchorPane getMainPane() { return mainPane; }

    public Scene getMainScene() { return mainScene; }

    public ViewManager() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setResizable(false);
        mainStage.setTitle("Project Platinum Bee");
        mainStage.setScene(mainScene);

        new MenuScene(mainPane);
    }

    public static void switchToPlaySoloScene(PlayerSprite playerSprite) {
        mainPane = null;
        mainPane = new AnchorPane();
        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage.setScene(scene);
        new PlaySoloScene(mainPane, playerSprite);
    }

    public static void switchToMenuScene() {
        mainPane = null;
        mainPane = new AnchorPane();
        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage.setScene(scene);
        new MenuScene(mainPane);
    }

}
