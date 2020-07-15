package geommetry;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * The type Draw line.
 */
public class DrawLine implements Sprite {
    private Point start;
    private Point end;
    private Color lineColor;

    /**
     * Instantiates a new Draw line.
     *
     * @param start the start
     * @param end   the end
     * @param color the color
     */
    public DrawLine(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.lineColor = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.lineColor);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

    @Override
    public void timePassed() {

    }
}
