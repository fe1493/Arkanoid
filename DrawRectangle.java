package geommetry;

import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * The type Draw rectangle.
 */
public class DrawRectangle implements Sprite {
    private Point point;
    private Point upperLeft;
    private double width;
    private double height;
    private Color rectangleColor;

    /**
     * Instantiates a new Draw rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public DrawRectangle(Point upperLeft, double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.rectangleColor = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.rectangleColor);
        d.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
    }
    @Override
    public void timePassed() {

    }
}
