package levelreader;

import collidable.Block;
import geommetry.Point;

import java.awt.Color;
import java.util.TreeMap;

/**
 * The type Block from string.
 */
public class BlockFromString implements BlockCreator {

    private DefaultBlock defaultBlock;
    private TreeMap<String, String> stringOfBlocks;
    private Integer hits = null;
    private Integer width = null;
    private Integer height = null;
    private String symbol = null;
    private TreeMap<Integer, Fill> filler = null;
    private Color stroke = null;


    /**
     * Instantiates a new Block from string.
     *
     * @param defaultBlock  the default block
     * @param stringTreeMap the string tree map
     */
    public BlockFromString(DefaultBlock defaultBlock, TreeMap<String, String> stringTreeMap) {
        this.height = 0;
        this.width = 0;
        this.hits = 0;
        this.defaultBlock = defaultBlock;
        this.stringOfBlocks = stringTreeMap;
        this.symbol = null;
        this.filler = null;
        this.stroke = null;
        this.filler = new TreeMap<>();
        for (String key : stringTreeMap.keySet()) {
            if (key.equals("height")) {
                this.height = Integer.parseInt(stringTreeMap.get(key));
            } else if (key.equals("width")) {
                this.width = Integer.parseInt(stringTreeMap.get(key));
            } else if (key.equals("hit_points")) {
                this.hits = Integer.parseInt(stringTreeMap.get(key));
            } else if (key.equals("stroke")) {
                this.stroke = Fill.fillFromsString(stringTreeMap.get(key)).getColor();
            } else if (key.equals("symbol")) {
                this.symbol = stringTreeMap.get(key);
            } else if (key.equals("fill")) {
                this.filler.put(1, Fill.fillFromsString(stringTreeMap.get(key)));
            } else if (key.startsWith("fill-")) {
                String[] s = key.split("-");
                this.filler.put(Integer.parseInt(s[1]), Fill.fillFromsString(stringTreeMap.get(key)));
            }
        }
        if (this.defaultBlock != null) {
            if (this.width == 0) {
                this.width = defaultBlock.getWidth();
            }
            if (this.height == 0) {
                this.height = defaultBlock.getHeight();
            }
            if (this.hits == 0) {
                this.hits = defaultBlock.getHits();
            }
            if (this.filler.size() < this.hits) {
                for (int i = this.filler.size(); i < this.hits; i++) {
                    Fill f;
                    if (this.defaultBlock.getFills().containsKey(i - 1)) {
                        f = this.defaultBlock.getFills().get(i - 1);
                    } else {
                        f = this.defaultBlock.getFills().get(1);
                    }
                    this.filler.put(i - 1, f);
                }
            }
            if (this.stroke == null && this.defaultBlock.getStroke() != null) {
                this.stroke = defaultBlock.getStroke();
            }
            if (this.symbol == null) {
                this.symbol = defaultBlock.getSymbol();
            }
        }
        if (this.height == null || this.width == null || this.hits == null || this.filler == null || this.symbol == null
        ) {
            throw new RuntimeException("Missing block specifications and no default option!");
        }
    }

    @Override
    public Block create(int xpos, int ypos) {
        Point point = new Point((double) xpos, (double) ypos);
        return new Block(point, this.getWidth(), this.getHeight(), this.getHits(), this.filler, this.symbol);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets default block.
     *
     * @return the default block
     */
    public DefaultBlock getDefaultBlock() {
        return defaultBlock;
    }

    /**
     * Gets string of blocks.
     *
     * @return the string of blocks
     */
    public TreeMap<String, String> getStringOfBlocks() {
        return stringOfBlocks;
    }

    /**
     * Gets hits.
     *
     * @return the hits
     */
    public Integer getHits() {
        return hits;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets block.
     *
     * @return the block
     */
    public BlockCreator getBlock() {
        return this;
    }

    /**
     * Gets filler.
     *
     * @return the filler
     */
    public TreeMap<Integer, Fill> getFiller() {
        return filler;
    }

    /**
     * Gets stroke.
     *
     * @return the stroke
     */
    public Color getStroke() {
        return stroke;
    }
}
