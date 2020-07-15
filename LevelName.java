package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Sprite;

import java.awt.Color;

/**
 * The type Level name.
 */
public class LevelName implements Sprite {
    private String levelName;

    /**
     * The constructor.
     *
     * @param levelName of the current level.
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(489, 17, this.toString(), 15);
    }

    @Override
    public String toString() {
        return "Level Name: " + this.levelName;
    }


    @Override
    public void timePassed() {

    }

    /**
     * Add to the  game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

