package erik.wiesi.view.playSolo;

import erik.wiesi.view.mainMenu.InfoPanel;

public class Score {

    private String name;
    private Integer score;
    private final InfoPanel scorePanel;
    private int defeatedEnemies;
    private Integer waves;
    private final InfoPanel wavesPanel;
    private final long startTime;
    private long playtime;

    /**
     * Creats new Score Object and creats new Starting Time in MilliSeconds
     */
    public Score() {
        score = 0;
        scorePanel = new InfoPanel(score.toString());
        defeatedEnemies = 0;
        waves = 0;
        wavesPanel = new InfoPanel("Wave " + waves.toString());
        startTime = System.currentTimeMillis();
    }

    /**
     * Sets Total Play Time
     */
    public void setPlaytime() {
        playtime = System.currentTimeMillis() - startTime;
    }

    /**
     * @param name sets name for this Score
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return returns this Score's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns the total achieved Score as Integer
     */
    public int getScore() {
        return score;
    }

    /**
     * @return returns this Score's InfoPanel
     */
    public InfoPanel getScorePanel() {
        return scorePanel;
    }

    /**
     * @return returns the total amount of the defeated Enemies
     */
    public int getDefeatedEnemies() {
        return defeatedEnemies;
    }

    /**
     * @return returns total amount of Waves survived
     */
    public int getWaves() {
        return waves;
    }

    /**
     * @return returns actual Wave as InfoPanel
     */
    public InfoPanel getWavesPanel() {
        return wavesPanel;
    }

    /**
     * @return returns total Playtime in Milliseconds
     */
    public long getPlayTime() {
        return playtime;
    }

    /**
     * adds next Wave to the Score
     */
    public void addWave() {
        waves++;
        wavesPanel.setText("Wave " + waves.toString());
    }

    /**
     * @param add adds Points to the Score
     */
    public void addScore(int add) {
        score += add;
        scorePanel.setText(score.toString());
        defeatedEnemies++;
    }
}
