package geommetry;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;
    /**
     * The type Fill circle.
     */
    public class FillCircle implements Sprite {
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
        public FillCircle(int r, Point center, Color color) {
            this.center = center;
            this.radius = r;
            this.circleColor = color;
        }

        @Override
        public void drawOn(DrawSurface d) {
            d.setColor(this.circleColor);
            d.fillCircle((int) this.center.getX(), (int) this.center.getY(), radius);
        }

        @Override
        public void timePassed() {

        }
    }
