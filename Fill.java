package levelreader;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * The type Fill.
 */
public class Fill {
    private Image image;
    private Color color;
    private boolean isImage = false;

    /**
     * Instantiates a new Fill.
     */
    public  Fill() {
        this.color = Color.BLACK;
        this.image = null;
        this.isImage = false;
    }

    /**
     * Instantiates a new Fill.
     *
     * @param c the c
     */
    public Fill(Color c) {
        this.color = c;
    }

    /**
     * Fill froms string to colors.
     *
     * @param s the s
     * @return the fill
     */
    public static Fill fillFromsString(String s) {
        Fill fill = new Fill();
        Color tempColor;
        if (s == null) {
            return null;
        }
        if (s.startsWith("image")) {
            try {
                int start = s.indexOf("(") + 1;
                int end = s.indexOf(")");
                String st = s.substring(start, end);
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(st);
                fill.image = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fill.isImage = true;
        } else if  (s.startsWith("color")) {
            if (s.contains("RGB")) {
                int start = s.indexOf("B") + 2;
                int end = s.indexOf(")");
                String st = s.substring(start, end);
                String[] t = st.split(",");
                fill.color = new Color(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]));
        } else {
                int start = s.indexOf("(") + 1;
                int end = s.indexOf(")");
                String st = s.substring(start, end);

                Field field2 = null;
                try {
                    field2 = Class.forName("java.awt.Color").getField(st);
                    tempColor = (Color) field2.get(null);
                } catch (Exception e) {
                    tempColor = null;
                }
                fill.color = tempColor;
                }
            }
        return fill;
    }


    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Is image boolean.
     *
     * @return the boolean
     */
    public boolean isImage() {
        return this.isImage;
    }
}
