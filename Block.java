package collidable;

import game.GameLevel;
import geommetry.Point;
import geommetry.Rectangle;
import biuoop.DrawSurface;
import hitlisteners.HitListener;
import hitlisteners.HitNotifier;
import levelreader.ColorChoice;
import levelreader.Fill;
import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * The block class.
 */
public class Block implements Cloneable, Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners = new ArrayList<>();
    private Rectangle ourBlocksRectangle;
    private GameLevel ourGame;
    private int hitPoints;
    private Color color;
    private TreeMap<Integer, Fill> fillMap = new TreeMap<>();
    private Image image;
    private ColorChoice cc;
    private String symbol;

    /**
     * The method constructs the block.
     *
     * @param rectangle the blocks rectangle
     * @param hitPoints the number of hit points
     * @param thisColor the color of the block
     */
    public Block(Rectangle rectangle, int hitPoints, Color thisColor) {
        this.ourBlocksRectangle = rectangle;
        this.hitPoints = hitPoints;
        this.color = thisColor;
        this.fillMap.put(1, new Fill(thisColor));

    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param hitPoints the hit points
     * @param fm        the fm
     * @param symbol    the symbol
     */
    public Block(Point upperLeft, double width, double height, int hitPoints,
                 TreeMap<Integer, Fill> fm, String symbol) {
        this.ourBlocksRectangle = new Rectangle(upperLeft, width, height);
        this.hitPoints = hitPoints;
        this.fillMap = fm;
        this.image = null;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param hitPoints the hits
     * @param symbol    the symbol
     */
    public Block(Point upperLeft, double width, double height, int hitPoints, String symbol) {
        this.fillMap.put(1, Fill.fillFromsString(symbol));
        this.ourBlocksRectangle = new Rectangle(upperLeft, width, height);
        this.hitPoints = hitPoints;
        this.image = null;
        this.hitListeners = new LinkedList<>();
    }
    /**
     * The method c returns the collision shape of the object.
     *
     * @return our blocks rectangle the
     */
    public Rectangle getCollisionRectangle() {
        return this.ourBlocksRectangle;
    }

    /**
     * The method notifies the object if it collided with the block and returns the new velocity after the hit.
     *
     * @param collisionPoint  the collision point of the object with the block
     * @param currentVelocity the moving objects current velocity
     * @param hitter the ball which is the hitter
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx, dy;
        dx = currentVelocity.getDx();
        dy = currentVelocity.getDy();
        if (this.hitPoints > 0) {
            this.hitPoints--;
        }
        //if there is a collision point on the top or bottom lines but not the right or left lines
        if (this.getCollisionRectangle().getTopLine().checkIfOnLine(collisionPoint)
                || this.getCollisionRectangle().getBottomLine().checkIfOnLine(collisionPoint)
                && !((this.getCollisionRectangle().getLeftLine().checkIfOnLine(collisionPoint)
                || this.getCollisionRectangle().getRightLine().checkIfOnLine(collisionPoint)))) {
            //change the velocity
            dy = -dy;
            this.notifyHit(hitter);

        }
            //if there is a collision point on the right or left lines but not on the top or bottom lines
         if (this.getCollisionRectangle().getLeftLine().checkIfOnLine(collisionPoint)
                || this.getCollisionRectangle().getRightLine().checkIfOnLine(collisionPoint)
                && !(this.getCollisionRectangle().getTopLine().checkIfOnLine(collisionPoint)
                || this.getCollisionRectangle().getBottomLine().checkIfOnLine(collisionPoint))) {
            //change the velocity
            dx = -dx;
            this.notifyHit(hitter);

        }
        //return the new velocity
        return new Velocity(dx, dy);
    }

    /**
     * The method notifys when there is a hit.
     *
     * @param hitter the ball hitter
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitlisteners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * The method draws the block on the surface.
     * @param d the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        int a;
        if (this.hitPoints == 0) {
            a = 1;
        } else {
            a = this.hitPoints;
        }
        if (this.fillMap.get(a) == null) {
            throw new RuntimeException("null pointer");
        }
        if (this.fillMap.get(a).getImage() != null) {
            this.image = this.fillMap.get(a).getImage();
            d.drawImage((int) this.ourBlocksRectangle.getUpperLeft().getX(),
                    (int) this.ourBlocksRectangle.getUpperLeft().getY(), this.image);

        } else {
            this.color = this.fillMap.get(a).getColor();
            d.setColor(this.color);
            d.fillRectangle((int) this.ourBlocksRectangle.getUpperLeft().getX(), (int)
                            this.ourBlocksRectangle.getUpperLeft().getY(), (int) this.ourBlocksRectangle.getWidth(),
                    (int) this.ourBlocksRectangle.getHeight());
            d.setColor(Color.black);
            d.drawRectangle((int) this.ourBlocksRectangle.getUpperLeft().getX(),
                    (int) this.ourBlocksRectangle.getUpperLeft().getY(),
                    (int) this.ourBlocksRectangle.getWidth(), (int) this.ourBlocksRectangle.getHeight());
            d.setColor(Color.white);
        }
    }

    //add later
    @Override
    public void timePassed() {

    }

    /**
     * The method adds the block to the game as a sprite and as a collidable.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        this.ourGame = g;
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The method removes the block from the game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    @Override
    public void addHitListener(HitListener hl) {
        //add the hit listener
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        //remove the hit listener
        this.hitListeners.remove(hl);
    }

    /**
     * The method is a getter for the hit points of the block.
     *
     * @return the number of hit points
     */
    public int getHitPoints() {
        return this.hitPoints;
    }
    /**
     * Clones the block.
     *
     * @return a cloned block.
     */
    @Override
    public Block clone() {
        try {
            return (Block) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("not a cloneable object!");
        }
    }
}