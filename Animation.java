package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the frame
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop the game.
     *
     * @return the boolean
     */
    boolean shouldStop();
    }

