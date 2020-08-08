package erik.wiesi.main;

import erik.wiesi.view.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    /**
     * Starts Primary Stage
     * @param primaryStage Primary Stage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Starts Application
     * @param args Starting Parameters
     */
    public static void main(String[] args) {
        launch(args);
    }

}