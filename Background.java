package levels;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Background.
 */
public class Background implements Sprite {
    private List<Sprite> listOfSpritesForBackground = new ArrayList<>();

    /**
     * Add to the list.
     *
     * @param s the s
     */
    public void addToList(Sprite s) {
        this.listOfSpritesForBackground.add(s);
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite s : this.listOfSpritesForBackground) {
            s.drawOn(d);
        }
    }
    @Override
    public void timePassed() {

    }
}
