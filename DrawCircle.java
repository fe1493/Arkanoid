package geommetry;
import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * The type Draw circle.
 */
public class DrawCircle implements Sprite {
    private Point center;
    private int radius;
    private Color circleColor;

    /**
     * Instantiates a new Draw circle.
     *
     * @param center  the center
     * @param r     the r
     * @param color the color
     */
    public DrawCircle(int r, Point center, Color color) {
        this.center = center;
        this.radius = r;
        this.circleColor = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.circleColor);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), radius);
        //d.fillCircle(radius,(int) this.center.getX(), (int) this.center.getY());
    }

    @Override
    public void timePassed() {

    }
}
