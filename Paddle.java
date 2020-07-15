package collidable;

import game.GameLevel;
import game.GameEnvironment;
import geommetry.Point;
import geommetry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;

/**
 * This class is a paddle class.
 */
public class Paddle implements Sprite, Collidable {
   private GameEnvironment environment;
   private GameLevel game;
   private biuoop.KeyboardSensor keyboard;
   private GUI gui;
   private Rectangle paddlesRectangle;
   private Double leftBorder;
   private Double rightBorder;
   private int boardWidth = 800;
   private int paddleSpeed;


   /**
    * The constructor for the paddle.
    *
    * @param width       the width of the paddle
    * @param height      the height of the paddle
    * @param leftBorder  the left border of the board
    * @param rightBorder the right border of the board
    * @param paddleSpeed the paddle speed
    */
   public Paddle(int width, int height, double leftBorder, double rightBorder, int paddleSpeed) {
      double paddleStartY = 580;
      this.leftBorder = leftBorder;
      this.rightBorder = rightBorder;
      Point paddleStartPoint = new Point(this.boardWidth / 2 - width / 2, paddleStartY);
      this.paddlesRectangle = new Rectangle(paddleStartPoint, width, height);
      this.paddleSpeed = paddleSpeed;
   }

   /**
    * The function moves the paddle left.
    */
   public void moveLeft() {
      Point newPointToTheLeft = new Point(this.getCollisionRectangle().getUpperLeft().getX() - this.paddleSpeed,
              this.getCollisionRectangle().getUpperLeft().getY());
      this.paddlesRectangle = new Rectangle(newPointToTheLeft, this.paddlesRectangle.getWidth(),
              this.paddlesRectangle.getHeight());
      }

   /**
    * The function moves the paddle right.
    */

   public void moveRight() {
      Point newPointToTheRight = new Point(this.getCollisionRectangle().getUpperLeft().getX() + this.paddleSpeed,
              this.getCollisionRectangle().getUpperLeft().getY());
      this.paddlesRectangle = new Rectangle(newPointToTheRight, this.paddlesRectangle.getWidth(),
              this.paddlesRectangle.getHeight());
   }

   /**
    * The function checks which key was pressed.
    */
   public void timePassed() {
      if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)
              && this.leftBorder < this.paddlesRectangle.getUpperLeft().getX()) {
         moveLeft();
      }
      if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
              && this.paddlesRectangle.getUpperRight().getX() < this.rightBorder) {
         moveRight();
      }
   }
   /**
    * The function draws the paddle.
    * @param d the surface
    */
   @Override

   public void drawOn(DrawSurface d) {
      //set the color
      d.setColor(Color.YELLOW);
      // draw the ball on the given DrawSurface
      d.fillRectangle((int) this.paddlesRectangle.getUpperLeft().getX(),
              (int) this.paddlesRectangle.getUpperLeft().getY(), (int) this.paddlesRectangle.getWidth(),
              (int) this.paddlesRectangle.getHeight());
   }
   /**
    * The function returns the paddles rectangle.
    * @return the paddles rectangle
    */
   public Rectangle getCollisionRectangle() {
      return this.paddlesRectangle;
   }

   /**
    * The function returns the new velocity of the ball depending on where it hits the paddle.
    * @param collisionPoint the collision point between ball and line
    * @param currentVelocity the current velocity of the ball
    * @param hitter the ball hitter
    * @return the new velocity of the ball
    */
   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
      double speed = 6;
      Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
      //the result is the place where the paddle was hit
      int result = lineSegemtOfPaddle(collisionPoint);
      if (currentVelocity.getDy() < 0) {
         return currentVelocity;
      }
      if (this.getCollisionRectangle().getLeftLine().checkIfOnLine(collisionPoint)
              || this.getCollisionRectangle().getRightLine().checkIfOnLine(collisionPoint)) {
          newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
         return newVelocity;
      }
      //if it hits the first part
      if (result == 1) {
         newVelocity = Velocity.fromAngleAndSpeed(300, speed);

         //the second part
      } else if (result == 2) {
         newVelocity = Velocity.fromAngleAndSpeed(330, speed);
         //the middle
      } else if (result == 3) {
         return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
         //the fourth part
      } else if (result == 4) {

         newVelocity = Velocity.fromAngleAndSpeed(30, speed);
         //the fifth part
      } else if (result == 5) {
         newVelocity = Velocity.fromAngleAndSpeed(60, speed);
      }
      return newVelocity;
   }

   /**
    * The function adds the paddle to the game.
    *
    * @param g the game
    */
   public void addToGame(GameLevel g) {
      this.game = g;
      g.addCollidable(this);
      g.addSprite(this);
      setGui(game.getGui());
      setKeyboard(game.getKeyboard());
      setEnvironment(game.getGameEnvironment());
   }

   /**
    * The function sets the gui.
    *
    * @param newGui the gui
    */
   public void setGui(GUI newGui) {
      this.gui = newGui;
   }

   /**
    * The function sets the keyboard.
    *
    * @param newKeyboard the keyboard
    */
   public void setKeyboard(KeyboardSensor newKeyboard) {
      this.keyboard = newKeyboard;
   }

   /**
    * The function sets the game environment.
    *
    * @param newEnvironment the game environment
    */
   public void setEnvironment(GameEnvironment newEnvironment) {
      this.environment = newEnvironment;
   }

   /**
    * The function checks which part of the line the collision point is on.
    *
    * @param collisionPoint the game environment
    * @return a number corresponding to the hit point
    */
   public int lineSegemtOfPaddle(Point collisionPoint) {
      //divide the paddle into 5 equal parts
      double equallyDividedPaddleSegments = this.paddlesRectangle.getWidth() / 5;
      //the first part
      Point firstPoint = new Point(this.paddlesRectangle.getUpperLeft().getX(),
              this.paddlesRectangle.getUpperLeft().getY());
      //the second part
      Point secondPoint = new Point(this.paddlesRectangle.getUpperLeft().getX() + equallyDividedPaddleSegments,
              this.paddlesRectangle.getUpperLeft().getY());
      //the third part
      Point thirdPoint = new Point(this.paddlesRectangle.getUpperLeft().getX() + 2  * equallyDividedPaddleSegments,
              this.paddlesRectangle.getUpperLeft().getY());
      //the fourth part
      Point fourthPoint = new Point(this.paddlesRectangle.getUpperLeft().getX() + 3  * equallyDividedPaddleSegments,
              this.paddlesRectangle.getUpperLeft().getY());
      //the fifth part
      Point fifthPoint = new Point(this.paddlesRectangle.getUpperLeft().getX() + 4  * equallyDividedPaddleSegments,
              this.paddlesRectangle.getUpperLeft().getY());
      //end of the paddle
      Point sixthPoint = new Point(this.paddlesRectangle.getUpperLeft().getX() + this.paddlesRectangle.getWidth(),
              this.paddlesRectangle.getUpperLeft().getY());
      //check if the ball hits the paddle and which part of the paddle it hit
      if (this.getCollisionRectangle().getTopLine().checkIfOnLine(collisionPoint)
              || this.getCollisionRectangle().getLeftLine().checkIfOnLine(collisionPoint)
               || this.getCollisionRectangle().getRightLine().checkIfOnLine(collisionPoint)) {
         //the first part of the line
         if (firstPoint.getX() <= collisionPoint.getX() && collisionPoint.getX() <= secondPoint.getX()) {
            return 1;
         }
         //the second part of the line
         if (secondPoint.getX() <= collisionPoint.getX() && collisionPoint.getX() <= thirdPoint.getX()) {
            return 2;
         }
         //the third part part of hte line
         if (thirdPoint.getX() <= collisionPoint.getX() && collisionPoint.getX() <= fourthPoint.getX()) {
            return 3;
         }
         //the fourth part part of hte line
         if (fourthPoint.getX() <= collisionPoint.getX() && collisionPoint.getX() <= fifthPoint.getX()) {
            return 4;
         }
         //the fifth part part of hte line
         if (fifthPoint.getX() <= collisionPoint.getX() && collisionPoint.getX() <= sixthPoint.getX()) {
            return 5;
         }
      }
      return 0;
   }
}