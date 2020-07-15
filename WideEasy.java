package levels;

import collidable.Block;
import geommetry.DrawRectangle;
import geommetry.Point;
import geommetry.FillCircle;
import geommetry.Rectangle;
import geommetry.DrawLine;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    private List<Velocity> velocityList;
    private List<Block> numberOfBlocks;
    private Background wideEasyBackground;
    private static final int NUMBEROFBALLS = 10;
    private static final int NUMBEROFBLOCKS = 15;
    private static final int SPEED = 6;
    private static final int ANGLE = -60;
    private static final int PADDLE_SPEED = 20;
    private static final int PADDLE_WIDTH = 500;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 52;
    private static final int HITPOINTS = 1;

    @Override
    public int numberOfBalls() {
        return NUMBEROFBALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        velocityList = new ArrayList<>();
        int angleChange = 0;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(ANGLE + angleChange, SPEED));
            angleChange = angleChange + 14;
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
        String levelName = "Wide Easy";
        return levelName;

    }

    @Override
    public Sprite getBackground() {
        wideEasyBackground = new Background();
        DrawRectangle backgroundColor = new DrawRectangle(new Point(10, 20), 780, 590,
                new Color(184, 56, 215));
        wideEasyBackground.addToList(backgroundColor);
        int lineChage = 0;
        for (int i = 0; i < 70; i++) {
            Point lineStartPoint = new Point(150, 100);
            Point lineEndPoint = new Point(lineChage, 230);
            DrawLine line = new DrawLine(lineStartPoint, lineEndPoint, new Color(233, 236, 165));
            wideEasyBackground.addToList(line);
            lineChage = lineChage + 10;
        }
        FillCircle circle1 = new FillCircle(40, new Point(150, 100), Color.YELLOW);
        FillCircle circle2 = new FillCircle(50, new Point(150, 100), Color.orange);
        FillCircle circle3 = new FillCircle(60, new Point(150, 100), new Color(233, 236, 165));
        wideEasyBackground.addToList(circle3);
        wideEasyBackground.addToList(circle2);
        wideEasyBackground.addToList(circle1);
        return wideEasyBackground;
    }

    @Override
    public List<Block> blocks() {
        numberOfBlocks = new ArrayList<>();
        int blockStartPointX = 10;
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            if (i < 2) {
                Point currentPoint = new Point(blockStartPointX, 230);
                Block blockToAdd = new Block(new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT), HITPOINTS,
                        Color.RED);
                numberOfBlocks.add(blockToAdd);
            }
            if (2 <= i && i < 4) {
                Point currentPoint = new Point(blockStartPointX, 230);
                Block blockToAdd = new Block(new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT), HITPOINTS,
                        Color.ORANGE);
                numberOfBlocks.add(blockToAdd);
            }
            if (4 <= i && i < 6) {
                Point currentPoint = new Point(blockStartPointX, 230);
                Block blockToAdd = new Block(new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT), HITPOINTS,
                        Color.YELLOW);
                numberOfBlocks.add(blockToAdd);
            }
            if (6 <= i && i < 9) {
                Point currentPoint = new Point(blockStartPointX, 230);
                Block blockToAdd = new Block(new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT), HITPOINTS,
                        Color.GREEN);
                numberOfBlocks.add(blockToAdd);
            }
            if (9 <= i && i < 11) {
                Point currentPoint = new Point(blockStartPointX, 230);
                Block blockToAdd = new Block(new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT), HITPOINTS,
                        Color.BLUE);
                numberOfBlocks.add(blockToAdd);
            }
            if (11 <= i && i < 13) {
                Point currentPoint = new Point(blockStartPointX, 230);
                Block blockToAdd = new Block(new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT), HITPOINTS,
                        Color.PINK);
                numberOfBlocks.add(blockToAdd);
            }
            if (13 <= i && i < 15) {
                Point currentPoint = new Point(blockStartPointX, 230);
                Block blockToAdd = new Block(new Rectangle(currentPoint, BLOCK_WIDTH, BLOCK_HEIGHT), HITPOINTS,
                        Color.CYAN);
                numberOfBlocks.add(blockToAdd);
            }
            blockStartPointX = blockStartPointX + BLOCK_WIDTH;
        }
        return numberOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBEROFBLOCKS;
    }
}