package levels;

import collidable.Block;
import geommetry.Point;
import geommetry.FillCircle;
import geommetry.DrawRectangle;
import geommetry.DrawLine;
import geommetry.Rectangle;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Final four.
 */
public class FinalFour implements LevelInformation {
    private List<Velocity> velocityList;
    private List<Block> numberOfBlocks;
    private Background background;
    private static final int NUMBEROFBALLS = 3;
    private static final int NUMBEROFBLOCKS = 105;
    private static final int SPEED = 6;
    private static final int ANGLE = 150;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 100;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 52;
    private static final int HITPOINTS = 1;
    private static final int ROW_OF_BLOCKS_START_POINT_X = 739;
    private static final int ROW_OF_BLOCKS_START_POINT_Y = 100;

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
            angleChange = angleChange + 59;
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
        String levelName = "Final Four";
        return levelName;

    }

    @Override
    public Sprite getBackground() {
        background = new Background();
        DrawRectangle backgroundColor = new DrawRectangle(new Point(10, 20), 780, 590,
                new Color(0, 157, 215));
        background.addToList(backgroundColor);
        int lineChage = 100;
        for (int i = 0; i < 8; i++) {
            Point lineStartPoint = new Point(lineChage, 480);
            Point lineEndPoint = new Point(lineChage - 10, 600);
            DrawLine line = new DrawLine(lineStartPoint, lineEndPoint, Color.WHITE);
            background.addToList(line);
            lineChage = lineChage + 10;
        }
        lineChage = 580;
        for (int i = 0; i < 8; i++) {
            Point lineStartPoint = new Point(lineChage, 530);
            Point lineEndPoint = new Point(lineChage - 15, 600);
            DrawLine line = new DrawLine(lineStartPoint, lineEndPoint, Color.WHITE);
            background.addToList(line);
            lineChage = lineChage + 10;
        }
        FillCircle cloud1 = new FillCircle(25, new Point(100, 450), Color.lightGray);
        background.addToList(cloud1);
        FillCircle cloud2 = new FillCircle(30, new Point(120, 480), Color.lightGray);
        background.addToList(cloud2);
        FillCircle cloud3 = new FillCircle(35, new Point(140, 440), Color.gray.brighter());
        background.addToList(cloud3);
        FillCircle cloud4 = new FillCircle(40, new Point(180, 450), Color.gray.brighter());
        background.addToList(cloud4);
        FillCircle cloud5 = new FillCircle(23, new Point(160, 490), Color.gray.brighter());
        background.addToList(cloud5);
        FillCircle cloud6 = new FillCircle(25, new Point(600, 500), Color.lightGray);
        background.addToList(cloud6);
        FillCircle cloud7 = new FillCircle(32, new Point(600, 530), Color.lightGray);
        background.addToList(cloud7);
        FillCircle cloud8 = new FillCircle(34, new Point(620, 515), Color.lightGray);
        background.addToList(cloud8);
        FillCircle cloud9 = new FillCircle(20, new Point(640, 527), Color.lightGray);
        background.addToList(cloud9);
        FillCircle cloud10 = new FillCircle(30, new Point(660, 517), Color.lightGray);
        background.addToList(cloud10);
        return background;
    }

    @Override
    public List<Block> blocks() {
        numberOfBlocks = new ArrayList<>();
        int j = ROW_OF_BLOCKS_START_POINT_X;
        int y = ROW_OF_BLOCKS_START_POINT_Y;
        for (int i = 0; i < 15; i++) {
            //new point placement - same for all the loops
            Point currentPoint = new Point(j, y);
            geommetry.Rectangle firstRowOFRectangles = new geommetry.Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block firstRow = new Block(firstRowOFRectangles, HITPOINTS, Color.gray);
            numberOfBlocks.add(firstRow);
            //for each block create the new one 50 coordiantes to the left - same for all the loops
            j = j - BLOCK_WIDTH;
        }
        //restart the next row one place below
        j = ROW_OF_BLOCKS_START_POINT_X;
        //second row of blocks
        for (int i = 0; i < 15; i++) {
            Point currentPoint = new Point(j, y + 20);
            geommetry.Rectangle secondRowOfRectangles = new geommetry.Rectangle(currentPoint,
                    BLOCK_WIDTH, BLOCK_HEIGHT);
            Block secondRowOfBlocks = new Block(secondRowOfRectangles, HITPOINTS, Color.RED);
            numberOfBlocks.add(secondRowOfBlocks);

            j = j - BLOCK_WIDTH;
        }
        j = ROW_OF_BLOCKS_START_POINT_X;
        //third row of blocks
        for (int i = 0; i < 15; i++) {
            Point currentPoint = new Point(j, y + 40);
            geommetry.Rectangle thirdRowOfRectangles = new geommetry.Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block thirdRowOfBlocks = new Block(thirdRowOfRectangles, HITPOINTS, Color.yellow);
            numberOfBlocks.add(thirdRowOfBlocks);

            j = j - BLOCK_WIDTH;
        }
        j = ROW_OF_BLOCKS_START_POINT_X;
        //fourth row of blocks
        for (int i = 0; i < 15; i++) {
            Point currentPoint = new Point(j, y + 60);
            geommetry.Rectangle fourthRowOfRectangles = new geommetry.Rectangle(currentPoint,
                    BLOCK_WIDTH, BLOCK_HEIGHT);
            Block fourthRowOfBlocks = new Block(fourthRowOfRectangles, HITPOINTS, Color.green);
            numberOfBlocks.add(fourthRowOfBlocks);

            j = j - BLOCK_WIDTH;
        }
        j = ROW_OF_BLOCKS_START_POINT_X;
        //fifth row of blocks
        for (int i = 0; i < 15; i++) {
            Point currentPoint = new Point(j, y + 80);
            geommetry.Rectangle fifthRowOfRectangles = new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block fifthRowOfBlocks = new Block(fifthRowOfRectangles, HITPOINTS, Color.WHITE);
            numberOfBlocks.add(fifthRowOfBlocks);
            j = j - BLOCK_WIDTH;
        }
        j = ROW_OF_BLOCKS_START_POINT_X;
        //sixth row of blocks
        for (int i = 0; i < 15; i++) {
            Point currentPoint = new Point(j, y + 100);
            geommetry.Rectangle sixthRowOfRectangles = new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block sixthRowOfBlocks = new Block(sixthRowOfRectangles, HITPOINTS, Color.PINK);
            numberOfBlocks.add(sixthRowOfBlocks);
            j = j - BLOCK_WIDTH;
        }
        j = ROW_OF_BLOCKS_START_POINT_X;
        //seventh row of blocks
        for (int i = 0; i < 15; i++) {
            Point currentPoint = new Point(j, y + 120);
            geommetry.Rectangle seventhRowOfRectangles = new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block seventhRowOfBlocks = new Block(seventhRowOfRectangles, 1, Color.CYAN);
            numberOfBlocks.add(seventhRowOfBlocks);
            j = j - BLOCK_WIDTH;
        }
        return numberOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBEROFBLOCKS;
    }
}
