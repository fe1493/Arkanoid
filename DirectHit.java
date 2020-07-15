package levels;

import collidable.Block;
import geommetry.DrawRectangle;
import geommetry.DrawCircle;
import geommetry.DrawLine;
import geommetry.Point;
import geommetry.Rectangle;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    private static final int NUMBEROFBALLS = 1;
    private static final int NUMBEROFBLOCKS = 1;
    private static final int SPEED = 6;
    private static final int ANGLE = 1;
    private static final int PADDLESPEED = 5;
    private static final int PADDLEWIDTH = 100;
    private static final int BLOCKHEIGHT = 30;
    private static final int BLOCKWIDTH = 30;
    private static final int HITPOINTS = 1;

    @Override
    public int numberOfBalls() {
        return NUMBEROFBALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        //might need to create a new velocity
        velocityList.add(Velocity.fromAngleAndSpeed(ANGLE, SPEED));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLESPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLEWIDTH;
    }

    @Override
    public String levelName() {
        String levelName = "Direct Hit";
        return levelName;

    }

    @Override
    public Sprite getBackground() {
        Background directHitBackground = new Background();
        Point point1 = new Point(260, 181);
        Point point2 = new Point(365, 181);
        Point point3 = new Point(435, 181);
        Point point4 = new Point(541, 181);
        Point point5 = new Point(400, 200);
        Point point6 = new Point(400, 327);
        Point point7 = new Point(400, 56);
        Point point8 = new Point(400, 150);
        DrawLine line1 = new DrawLine(point1, point2, Color.BLUE);
        DrawLine line2 = new DrawLine(point3, point4, Color.BLUE);
        DrawLine line3 = new DrawLine(point5, point6, Color.BLUE);
        DrawLine line4 = new DrawLine(point7, point8, Color.BLUE);
        DrawCircle circle1 = new DrawCircle(60, new Point(400, 181), Color.BLUE);
        DrawCircle circle2  = new DrawCircle(90, new Point(400, 181), Color.BLUE);
        DrawCircle circle3 = new DrawCircle(120, new Point(400, 181), Color.BLUE);
        DrawRectangle backgroundColor = new DrawRectangle(new Point(10, 20), 780, 590, Color.BLACK);
        directHitBackground.addToList(backgroundColor);
        directHitBackground.addToList(line1);
        directHitBackground.addToList(line2);
        directHitBackground.addToList(line3);
        directHitBackground.addToList(line4);
        directHitBackground.addToList(circle1);
        directHitBackground.addToList(circle2);
        directHitBackground.addToList(circle3);
        return directHitBackground;
    }

    @Override
    public List<Block> blocks() {
        List<Block> numberOfBlocks = new ArrayList<>();
        Point currentPoint = new Point(387, 163);
        Rectangle shapeOfBlock = new Rectangle(currentPoint, BLOCKWIDTH, BLOCKHEIGHT);
        Block blockToAdd = new Block(shapeOfBlock, HITPOINTS, Color.RED);
        numberOfBlocks.add(blockToAdd);
        return numberOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBEROFBLOCKS;
    }
}