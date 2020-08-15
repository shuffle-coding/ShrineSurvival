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
    private static Stage mainStage;

    /**
     * @return returns MainStage
     */
    public Stage getMainStage() {
        return mainStage;
    }

    /**
     * @return returns total Width of this Window
     */
    public static double getWIDTH() { return WIDTH; }

    /**
     * @return returns total Height of this Window
     */
    public static double getHEIGHT() { return HEIGHT; }

    /**
     * @return returns Main AnchorPane to be drawn at
     */
    public AnchorPane getMainPane() { return mainPane; }

    /**
     * Creats new ViewManager Builds AnchorPane, Stage and Scene
     */
    public ViewManager() {
        mainPane = new AnchorPane();
        Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setResizable(false);
        mainStage.setTitle("Project Platinum Bee");
        mainStage.setScene(mainScene);

        new MenuScene(mainPane);
    }

    /**
     * Switches to PlaySoloScene,
     * needs PlayerSprite to be shown in Game
     * @param playerSprite Player's chosen PlayerSprite
     * @param playerName player'name
     */
    public static void switchToPlaySoloScene(PlayerSprite playerSprite, String playerName) {
        mainPane = null;
        mainPane = new AnchorPane();
        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage.setScene(scene);
        new PlaySoloScene(mainPane, playerSprite, playerName);
    }

    /**
     * Switches back to the Menu Scene
     */
    public static void switchToMenuScene() {
        mainPane = null;
        mainPane = new AnchorPane();
        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage.setScene(scene);
        new MenuScene(mainPane);
    }

}
