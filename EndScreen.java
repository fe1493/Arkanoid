package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import counter.Counter;
import geommetry.DrawRectangle;
import geommetry.Point;
import levels.Background;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private Counter lives;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k     the k
     * @param score the score
     * @param lives the lives
     */
    public EndScreen(KeyboardSensor k, Counter score, Counter lives) {
        this.keyboard = k;
        this.stop = false;
        this.lives = lives;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.lives.getValue() == 0) {
            Background loseBackground = new Background();
            DrawRectangle backgroundColor = new DrawRectangle(new Point(0, 0), 800, 600,
                    new Color(164, 0, 22));
            loseBackground.addToList(backgroundColor);
            loseBackground.drawOn(d);
            d.setColor(Color.WHITE);
            d.drawText(200, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
            d.drawText(250, d.getHeight() / 2 + 100, "Better luck next time!!", 32);

        } else {
            Background winBackground = new Background();
            DrawRectangle backgroundColor = new DrawRectangle(new Point(0, 0), 800, 600,
                    new Color(0, 62, 176));
            winBackground.addToList(backgroundColor);
            winBackground.drawOn(d);
            d.setColor(Color.GREEN);
            d.drawText(200, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
            d.drawText(250, d.getHeight() / 2 + 100, "Great Job!!!", 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;

        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}