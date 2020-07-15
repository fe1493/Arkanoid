package sprites;

import biuoop.DrawSurface;

/**
 * The sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the draw surface
     */
    void drawOn(DrawSurface d);
    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
