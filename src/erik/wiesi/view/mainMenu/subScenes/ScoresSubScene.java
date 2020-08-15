package erik.wiesi.view.mainMenu.subScenes;

import erik.wiesi.model.ShrineSurvivalSubScene;
import erik.wiesi.sprites.PlayerSprite;
import erik.wiesi.sprites.Spritesheet;
import erik.wiesi.view.mainMenu.InfoPanel;
import erik.wiesi.view.playSolo.handler.DatabaseConnection;
import javafx.scene.canvas.Canvas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoresSubScene  extends ShrineSurvivalSubScene {

    private final Spritesheet CHARS = new Spritesheet("/SpriteSheets/roguelikeChar_transparent.png");

    /**
     * Shows Top 10 Scores and thier player Sprites
     * gets Data from Database
     */
    public ScoresSubScene() throws SQLException {
        Connection con = new DatabaseConnection().getConnection();

        String sql = "SELECT * FROM score JOIN player_model ON score.player_model_id = player_model.player_model_id ORDER BY score.score DESC LIMIT 10;";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.execute();
        ResultSet result = stmt.getResultSet();

        int[] body, pants, shoes, top, head;

        int i = 0;
        double startX = this.getPane().getWidth() / 10;
        double startY = this.getPane().getHeight() / 8;

        while (result.next() && i < 10){
            body = new int[] {result.getInt("body_x"), result.getInt("body_y")};
            pants = new int[] {result.getInt("pants_x"), result.getInt("pants_y")};
            shoes = new int[] {result.getInt("shoes_x"), result.getInt("shoes_y")};
            top = new int[] {result.getInt("top_x"), result.getInt("top_y")};
            head = new int[] {result.getInt("head_x"), result.getInt("head_y")};
            int score = result.getInt("score");
            String name = result.getString("player_name");
            String playTime = result.getString("play_time");

            Canvas playerSprite = new PlayerSprite(CHARS, body, pants, shoes, top, head).getCanvas();
            InfoPanel scorePanel = new InfoPanel(Integer.toString(score));
            InfoPanel namePanel = new InfoPanel(name);
            InfoPanel playTimePanel = new InfoPanel(playTime);

            this.getPane().getChildren().add(playerSprite);
            this.getPane().getChildren().add(scorePanel);
            this.getPane().getChildren().add(namePanel);
            this.getPane().getChildren().add(playTimePanel);

            playerSprite.setLayoutX(startX);
            playerSprite.setLayoutY(startY);
            playerSprite.setScaleX(2);
            playerSprite.setScaleY(2);

            scorePanel.setLayoutX(startX + 50);
            scorePanel.setLayoutY(startY);

            namePanel.setLayoutX(startX + 150);
            namePanel.setLayoutY(startY);

            playTimePanel.setLayoutX(startX + 380);
            playTimePanel.setLayoutY(startY);

            startY+= 50;
            i++;
        }
        result.close();
        stmt.close();
        con.close();
    }
}
