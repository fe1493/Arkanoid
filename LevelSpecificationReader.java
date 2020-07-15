package levelreader;
import collidable.Block;
import levels.LevelInformation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader  {

    /**
     * The constant NUMBER_OF_FIELDS.
     */
    public static final int NUMBER_OF_FIELDS = 9;

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.BufferedReader reader) {
        List<String> levelInformation = new ArrayList<>();
        List<LevelInformation> levels = new ArrayList<>();
        String line;
        String[] defineBlockFile;
        BufferedReader read = reader;
        BlocksFromSymbolsFactory factory = null;
        int x;
        int y;
        int numberOfBlocks = 0;
        String runningChar;
        try {
            line = read.readLine();
            while (line != null) {
                if (line.startsWith(" ") || line.startsWith("#") || line.length() == 0) {
                    line = read.readLine();
                } else if (line.startsWith("START_LEVEL")) {
                    do {
                        if (line.startsWith("block_definitions")) {
                            defineBlockFile = line.split(":");
                            try {
                                InputStream is = ClassLoader.getSystemClassLoader().
                                        getResourceAsStream(defineBlockFile[1]);
                                factory = BlocksDefinitionReader.fromReader(new BufferedReader(new
                                        InputStreamReader(is)));
                            } catch (Exception e) {
                                System.out.println("Something went wrong");
                                e.printStackTrace();
                        }
                    } else if (line.contains(":")) {
                            levelInformation.add(line);
                        }
                        line = read.readLine();
                } while (!line.startsWith("START_BLOCKS"));
                        if (levelInformation.size() < NUMBER_OF_FIELDS) {
                            throw new RuntimeException("Missing fields for levels");
                        }
                        if (line.startsWith("START_BLOCKS")) {
                            //list thst will contain all the blocks of the level
                            ArrayList<Block> blocksForLevels = new ArrayList<>();
                            //levelInfo info
                            LevelInfo levelInfo = (LevelInfo) specifyLevelInforation(levelInformation);
                            x = levelInfo.getStartingX();
                            y = levelInfo.getStartingY();
                            line = read.readLine();
                            while (!line.startsWith("END_BLOCKS")) {
                                // make sure x is at return value after each row
                                for (int i = 0; i < line.length(); i++) {
                                    runningChar = String.valueOf(line.charAt(i));
                                    if (factory.isBlockSymbol(runningChar)) {
                                        int x1 = x + numberOfBlocks;
                                        int t = factory.getBlockCreators().get(runningChar).getWidth();
                                        blocksForLevels.add(factory.getBlock(runningChar, x + (numberOfBlocks
                                                * (factory.getBlockCreators().get(runningChar).getWidth())), y));
                                        numberOfBlocks++;
                                    } else if (factory.isSpaceSymbol(runningChar)) {
                                        x += factory.getSpaceWidth(runningChar);
                                    }
                                }
                                    //get the next row
                                    y += levelInfo.getRowHeight();
                                    // put x back to original value
                                    x = levelInfo.getStartingX();
                                    //reset the number of blocks
                                    numberOfBlocks = 0;
                                    line = read.readLine();
                                }
                            levelInfo.setBlocks(blocksForLevels);
                            levels.add(levelInfo);
                            line = read.readLine();

                            if (line.startsWith("END_LEVEL")) {
                                levelInformation.removeAll(levelInformation);
                                line = read.readLine();
                                continue;

                            }
                            levelInfo.initialBallVelocities();
                            }
                        }
                    }
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            if (reader != null) { // Exception might have happened at constructor
                try {
                    reader.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }

        }
        return levels;
    }

    /**
     * A help function to receive the background.
     *
     * @param levelsDefinitons the level definitions from the file.
     * @return a level information object of the level from file.
     */
    public LevelInformation specifyLevelInforation(List<String> levelsDefinitons) {
        TreeMap map = new TreeMap();
        String[] seperateDefinitions;
        for (String definition : levelsDefinitons) {
            seperateDefinitions = definition.split(":");
            map.put(seperateDefinitions[0], seperateDefinitions[1]);
        }
        return new LevelInfo(map);
    }

//    /**
//     * The entry point of application.
//     *
//     * @param args the input arguments
//     * @throws IOException the io exception
//     */
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(new
//                FileInputStream("level_definitions.txt"))
//        );
//        LevelSpecificationReader l = new LevelSpecificationReader();
//        List<LevelInformation> list = l.fromReader(br);
//    }
}
