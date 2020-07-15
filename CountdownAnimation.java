package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numberOfSeconds;
    private int countDownFrom;
    private SpriteCollection theGameScreen;
    private double pauseTime;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numberOfSeconds = numOfSeconds;
        this.countDownFrom = countFrom;
        this.theGameScreen = gameScreen;
        this.pauseTime = numberOfSeconds / countDownFrom;
         }
    @Override
    public void doOneFrame(DrawSurface d) {
        theGameScreen.drawAllOn(d);
        if (countDownFrom > 0) {
            d.setColor(Color.BLUE);
            d.drawText(350, d.getHeight() / 2, String.valueOf(countDownFrom), 80);
            countDownFrom--;
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor((long) pauseTime);

        } else {
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor((long) pauseTime);
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}