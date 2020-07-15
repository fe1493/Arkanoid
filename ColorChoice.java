package levelreader;

import java.awt.Image;
import java.awt.Color;

/**
 * The type Color choice.
 */
public class ColorChoice {

    private boolean isImage2 = false;
    private Color color = null;
    private Image im = null;
    private Fill f;

    /**
     * The constructor.
     *
     * @param f the Fill object.
     */
    public ColorChoice(Fill f) {
        this.f = f;
    }

    /**
     * Sets the color.
     *
     * @return void.
     */
    public boolean setColor() {
        if (this.f == null) {
            return false;
        }
        if (!this.f.isImage()) {
            this.color = this.f.getColor();
        } else {
            this.isImage2 = true;
            this.im = this.f.getImage();
        }
        return true;
    }

    /**
     * See return.
     *
     * @return the color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * See return.
     *
     * @return the image.
     */
    public Image getImage() {
        return this.im;
    }

}