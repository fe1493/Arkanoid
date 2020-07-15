package levelreader;

import collidable.Block;

import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param sw the sw
     * @param bc the bc
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> sw, Map<String, BlockCreator> bc) {
        this.blockCreators = bc;
        this.spacerWidths = sw;
    }

    /**
     * Gets spacer widths.
     *
     * @return the spacer widths
     */
    public Map<String, Integer> getSpacerWidths() {
        return spacerWidths;
    }

    /**
     * Gets block creators.
     *
     * @return the block creators
     */
    public Map<String, BlockCreator> getBlockCreators() {
        return blockCreators;
    }

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return returns true if 's' is a valid space symbol.
     */

    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);

    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return  true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
            return this.blockCreators.containsKey(s);
    }

    /**
     * Gets block.
     *
     * @param s    the s
     * @param xpos the xpos
     * @param ypos the ypos
     * @return a block according to the definitions associatedwith symbol s.
     * The block will be located at position (xpos, ypos).
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Get space width int.
     *
     * @param s the  width in pixels associated with the given spacer-symbol.
     * @return the int
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}