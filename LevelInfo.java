package levelreader;


import collidable.Block;
import geommetry.Point;
import levels.LevelInformation;
import sprites.Sprite;
import sprites.Velocity;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The type Background.
 */
public class LevelInfo implements LevelInformation {
    private List<Sprite> listOfSpritesForBackground = new ArrayList<>();
    private int numOfBalls;
    private int paddleWidth;
    private int paddleSpeed;
    private int numberOfBlocksToRemove;
    private String ballVelocities;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private String back;

    private List<Block> blockList;
    private String levelName;

    /**
     * Instantiates a new Level info.
     *
     * @param map the map
     */
    public LevelInfo(TreeMap map) {
        this.paddleWidth = Integer.parseInt((String) map.get("paddle_width"));
        this.paddleSpeed = Integer.parseInt((String) map.get("paddle_speed"));
        this.numOfBalls = 1;
        this.levelName = (String) map.get("level_name");
        this.numberOfBlocksToRemove = Integer.parseInt((String) map.get("num_blocks"));
        this.ballVelocities = (String) map.get("ball_velocities");
        this.rowHeight = Integer.parseInt((String) map.get("row_height"));
        this.blocksStartX = Integer.parseInt((String) map.get("blocks_start_x"));
        this.blocksStartY = Integer.parseInt((String) map.get("blocks_start_y"));
        this.back = (String) map.get("background");
        this.initialBallVelocities();
    }


    /**
     * Add to the list.
     *
     * @param s the s
     */
    public void addToList(Sprite s) {
        this.listOfSpritesForBackground.add(s);
    }


    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * The initial velocity of each ball .
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        String[] t = this.ballVelocities.split(" ");
        String[] y;
        for (int i = 0; i < t.length; i++) {
            y = t[i].split(",");
            velocities.add(Velocity.fromAngleAndSpeed(Integer.parseInt(y[0]), Integer.parseInt(y[1])));
        }
        this.numOfBalls = velocities.size();
        return velocities;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Returns a sprite with the Background of the level.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return new Block(new Point(0, 0), 800, 600, 0,
                this.back);
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location..
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (Block block : this.blockList) {
            blocks.add(block.clone());
        }
        return blocks;
    }

    /**
     * Sets blocks.
     *
     * @param bl the bl
     */
    public void setBlocks(List<Block> bl) {
        this.blockList = bl;
    }
    /**
     * Number of levels that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * Gets starting x.
     *
     * @return the starting x
     */
    public int getStartingX() {
        return this.blocksStartX;
    }

    /**
     * See return.
     * <p>
     *
     * @return he first blocks Y value on the row.
     */
    public int getStartingY() {
        return this.blocksStartY;
    }

    /**
     * See return.
     *
     * @return the row height.
     */
    public int getRowHeight() {
        return this.rowHeight;
    }
}