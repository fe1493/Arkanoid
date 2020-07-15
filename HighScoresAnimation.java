package scores;

import animation.Animation;
import biuoop.DrawSurface;


import java.awt.Color;
import static java.lang.String.valueOf;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scoresTable;
    private boolean stop;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scoresTable the scores table
     */
    public HighScoresAnimation(HighScoresTable scoresTable) {
        this.scoresTable = scoresTable;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(106, 152, 180));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawText(150, 70, "High Scores", 60);
        d.setColor(Color.GREEN);
        d.drawText(100, 130, "Player Name", 30);
        d.drawText(450, 130, "Score", 30);
        d.drawText(100, 150, "_____________________________________________________________________________",
                15);
        int y = 200;
        for (ScoreInfo s : this.scoresTable.getHighScores()) {
            d.setColor(Color.ORANGE);
            d.drawText(100,  y,   s.getName(), 30);
            d.drawText(450,  y, valueOf(s.getScore()), 30);
            y += 40;
        }
        d.setColor(Color.MAGENTA);
        d.drawText(100, 550, "Press Space To Continue", 32);
    }

    /**
     * Should stop the game.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
