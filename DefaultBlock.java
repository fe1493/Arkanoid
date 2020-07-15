package levelreader;

import java.awt.Color;
import java.util.TreeMap;

/**
 * The type Default block.
 */
public class DefaultBlock {
    private int hits;
    private int width;
    private int height;
    private String symbol;
    private Color stroke;
    private TreeMap<Integer, Fill> fills;
    private TreeMap<String, String> defineLineMap = null;

    /**
     * Instantiates a new Default block.
     *
     * @param defLineMap the def line map
     */
    public DefaultBlock(TreeMap<String, String> defLineMap) {
        this.width = 0;
        this.hits = 0;
        this.height = 0;
        this.stroke = null;
        this.symbol = null;
        this.fills = new TreeMap<Integer, Fill>();
        this.defineLineMap = defLineMap;
    }

    /**
     * Create default block.
     */
    public void createDefaultBlock() {
        for (String key : this.defineLineMap.keySet()) {
            if (key.equals("height")) {
                this.height = Integer.parseInt(this.defineLineMap.get(key));
            } else if (key.equals("width")) {
                this.width = Integer.parseInt(this.defineLineMap.get(key));
            } else if (key.equals("hit_points")) {
                this.hits = Integer.parseInt(this.defineLineMap.get(key));
            } else if (key.equals("stroke")) {
                this.stroke = Fill.fillFromsString(this.defineLineMap.get(key)).getColor();
            } else if (key.equals("symbol")) {
                String[] str = key.split("-");
                this.fills.put(Integer.parseInt(str[1]), Fill.fillFromsString(this.defineLineMap.get(key)));
            }
        }

    }

    /**
     * Gets hits.
     *
     * @return the hits
     */
    public int getHits() {
        return hits;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Gets stroke.
     *
     * @return the stroke
     */
    public Color getStroke() {
        return this.stroke;
    }

    /**
     * Gets fills.
     *
     * @return the fills
     */
    public TreeMap<Integer, Fill> getFills() {
        return this.fills;
    }

    /**
     * Gets define line map.
     *
     * @return the define line map
     */
    public TreeMap<String, String> getDefineLineMap() {
        return this.defineLineMap;
    }
}
