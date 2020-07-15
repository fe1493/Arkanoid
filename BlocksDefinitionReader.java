package levelreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.TreeMap;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    private List<String> lineOfBlocks;
    private String defLine;
    private List<String> spacerLine;
    private BufferedReader bufferedReader;
    private String temp;
    private DefaultBlock defaultBlock;
    private TreeMap<String, BlockCreator> blockList;
    private TreeMap<String, Integer> spacersList;

    /**
     * Instantiates a new Blocks definition reader.
     */
    public BlocksDefinitionReader() {
        this.lineOfBlocks = new ArrayList<>();
        this.defLine = null;
        this.spacerLine = null;
        this.bufferedReader = null;
        this.spacersList = new TreeMap<>();
        this.blockList = new TreeMap<>();
        this.spacerLine = new ArrayList<>();
        this.defaultBlock = null;
    }

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader reader1 = new BufferedReader(reader);
        BlocksDefinitionReader blocksDefinitionReader = new BlocksDefinitionReader();
        blocksDefinitionReader.readDef(reader1);
        return new BlocksFromSymbolsFactory(blocksDefinitionReader.getSpacersList(),
                blocksDefinitionReader.getBlockList());
    }

    /**
     * Read def.
     *
     * @param reader the reader
     */
    public void readDef(BufferedReader reader) {
        try {
            this.bufferedReader = reader;
            temp = this.bufferedReader.readLine();
            while (temp != null) {
                if (temp.startsWith("#") || temp.startsWith(" ") || temp.length() == 0) {
                    temp = reader.readLine();
                }
                if (temp.startsWith("default")) {
                    this.defLine = temp;
                    this.defaultBlock = new DefaultBlock(lineOfBlocksTreeMap(this.defLine));
                    this.defaultBlock.createDefaultBlock();
                }
                if (temp.startsWith("bdef")) {
                    this.lineOfBlocks.add(temp);
                    BlockFromString b = new BlockFromString(this.defaultBlock, this.lineOfBlocksTreeMap(temp));
                    this.blockList.put(b.getSymbol(), b.getBlock());
                }
                if (temp.startsWith("sdef")) {
                    this.spacerLine.add(temp);
                }
                temp = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong while reading!");
        }

    }

    /**
     * Line of blocks tree map tree map.
     *
     * @param s the s
     * @return the tree map
     */
    public TreeMap<String, String> lineOfBlocksTreeMap(String s) {
        String[] str = s.split(" ");
        TreeMap<String, String> defaultMap = new TreeMap<>();
        for (int i = 1; i  < str.length; i++) {
            String[] value = str[i].split(":");
            defaultMap.put(value[0], value[1]);
        }
        return defaultMap;
    }

    /**
     * Spacers key map tree map.
     *
     * @param spacerList the spacer list
     * @return the tree map
     */
    public TreeMap<String, Integer> spacersKeyMap(List<String> spacerList) {
        TreeMap<String, Integer> spacerMapWidths = new TreeMap<String, Integer>();
        String[] str;

        for (String s : spacerList) {
            str = s.split(" ");
            String[] symbolSplit = str[1].split(":");
            String key = symbolSplit[1];
            String[] widthSplit = str[2].split(":");
            Integer value = Integer.parseInt(widthSplit[1]);
            spacerMapWidths.put(key, value);
        }
        this.spacersList = spacerMapWidths;
        return spacerMapWidths;
    }

    /**
     * Gets block list.
     *
     * @return the block list
     */
    public TreeMap<String, BlockCreator> getBlockList() {
        return blockList;
    }

    /**
     * Gets spacers list.
     *
     * @return the spacers list
     */
    public TreeMap<String, Integer> getSpacersList() {
        return spacersList;
    }


}