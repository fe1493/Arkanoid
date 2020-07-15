package levels;

import collidable.Block;
import geommetry.DrawRectangle;
import geommetry.FillCircle;
import geommetry.Point;
import geommetry.Rectangle;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    private List<Velocity> velocityList;
    private List<Block> numberOfBlocks;
    private Background background;
    private static final int NUMBEROFBALLS = 2;
    private static final int NUMBEROFBLOCKS = 40;
    private static final int SPEED = 6;
    private static final int ANGLE = 160;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 100;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 50;
    private static final int HITPOINTS = 1;
    private static final int ROW_OF_BLOCKS_START_POINT_X = 740;
    private static final int ROW_OF_BLOCKS_START_POINT_Y = 200;

    @Override
    public int numberOfBalls() {
        return NUMBEROFBALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        velocityList = new ArrayList<>();
        int angleChange = ANGLE;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(ANGLE + angleChange, SPEED));
            angleChange = ANGLE + 87;
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        String levelName = "Green 3";
        return levelName;

    }

    @Override
    public Sprite getBackground() {
        background = new Background();
        DrawRectangle backgroundColor = new DrawRectangle(new Point(10, 20), 780, 590,
                new Color(0, 127, 12));
        background.addToList(backgroundColor);
        DrawRectangle building = new DrawRectangle(new Point(70, 450), 100, 350, Color.BLACK);
        background.addToList(building);
        DrawRectangle lowerSpire = new DrawRectangle(new Point(105, 400), 30, 50,
                new Color(24, 24, 35));
        background.addToList(lowerSpire);
        DrawRectangle upperSpire = new DrawRectangle(new Point(115, 219), 10, 180, Color.gray);
        background.addToList(upperSpire);
        FillCircle circle1 = new FillCircle(10, new Point(120, 216), Color.ORANGE);
        FillCircle circle2 = new FillCircle(7, new Point(120, 216), Color.red);
        FillCircle circle3 = new FillCircle(4, new Point(120, 216), Color.white);
        background.addToList(circle1);
        background.addToList(circle2);
        background.addToList(circle3);


        int y = 460;
        int width = 8;
        int height = 20;
        for (int j = 0; j < 8; j++) {
            int x = 76;
            for (int i = 0; i < 6; i++) {
                //new point placement - same for all the loops
                DrawRectangle firstRowOfWindows =
                        new DrawRectangle(new Point(x, y), width, height, Color.WHITE);
                background.addToList(firstRowOfWindows);
                x = x + width + width;
            }
            y += 30;
        }

        return background;
    }

    @Override
    public List<Block> blocks() {
        numberOfBlocks = new ArrayList<>();
        int j = ROW_OF_BLOCKS_START_POINT_X;
        int y = ROW_OF_BLOCKS_START_POINT_Y;
        for (int i = 0; i < 10; i++) {
            //new point placement - same for all the loops
            Point currentPoint = new Point(j, y);
            Rectangle firstRowOFRectangles = new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block firstRow = new Block(firstRowOFRectangles, HITPOINTS, Color.gray);
            numberOfBlocks.add(firstRow);
            //for each block create the new one 50 coordiantes to the left - same for all the loops
            j = j - BLOCK_WIDTH;
        }
        //restart the next row one place below
        j = ROW_OF_BLOCKS_START_POINT_X;
        //second row of blocks
        for (int i = 0; i < 9; i++) {
            Point currentPoint = new Point(j, y + 20);
            Rectangle secondRowOfRectangles = new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block secondRowOfBlocks = new Block(secondRowOfRectangles, HITPOINTS, Color.RED);
            numberOfBlocks.add(secondRowOfBlocks);

            j = j - BLOCK_WIDTH;
        }
        j = ROW_OF_BLOCKS_START_POINT_X;
        //third row of blocks
        for (int i = 0; i < 8; i++) {
            Point currentPoint = new Point(j, y + 40);
            Rectangle thirdRowOfRectangles = new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block thirdRowOfBlocks = new Block(thirdRowOfRectangles, HITPOINTS, Color.yellow);
            numberOfBlocks.add(thirdRowOfBlocks);

            j = j - BLOCK_WIDTH;
        }
        j = ROW_OF_BLOCKS_START_POINT_X;
        //fourth row of blocks
        for (int i = 0; i < 7; i++) {
            Point currentPoint = new Point(j, y + 60);
            Rectangle fourthRowOfRectangles = new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block fourthRowOfBlocks = new Block(fourthRowOfRectangles, HITPOINTS, Color.blue);
            numberOfBlocks.add(fourthRowOfBlocks);

            j = j - BLOCK_WIDTH;
        }
        j = ROW_OF_BLOCKS_START_POINT_X;
        //fifth row of blocks
        for (int i = 0; i < 6; i++) {
            Point currentPoint = new Point(j, y + 80);
            Rectangle fifthRowOfRectangles = new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block fifthRowOfBlocks = new Block(fifthRowOfRectangles, HITPOINTS, Color.WHITE);
            numberOfBlocks.add(fifthRowOfBlocks);
            j = j - BLOCK_WIDTH;
        }
        return numberOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBEROFBLOCKS;
    }
}

