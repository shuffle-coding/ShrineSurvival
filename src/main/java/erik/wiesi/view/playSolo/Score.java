package erik.wiesi.view.playSolo;

import erik.wiesi.view.mainMenu.InfoPanel;

public class Score {

    private Integer score;
    private InfoPanel scorePanel;
    private int defeatedEnemies;
    private Integer waves;
    private InfoPanel wavesPanel;

    public Score() {
        score = 0;
        scorePanel = new InfoPanel(score.toString());
        defeatedEnemies = 0;
        waves = 0;
        wavesPanel = new InfoPanel("Wave " + waves.toString());
    }

    public int getScore() {
        return score;
    }
    public InfoPanel getScorePanel() {
        return scorePanel;
    }
    public int getDefeatedEnemies() {
        return defeatedEnemies;
    }
    public int getWaves() {
        return waves;
    }
    public InfoPanel getWavesPanel() {
        return wavesPanel;
    }
    public void addWave() {
        waves++;
        wavesPanel.setText("Wave " + waves.toString());
    }
    public void addScore(int add) {
        score += add;
        scorePanel.setText(score.toString());
        defeatedEnemies++;
    }
}
