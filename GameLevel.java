package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.KeyboardSensor;
import collidable.Collidable;
import geommetry.Point;
import geommetry.Rectangle;
import hitlisteners.LivesIndicator;
import scores.ScoreTrackingListener;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import scores.ScoreIndicator;
import collidable.Paddle;
import collidable.Block;
import counter.Counter;
import levels.LevelInformation;
import levels.LevelName;
import sprites.Ball;
import sprites.Sprite;
import sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

/**
 * The game class.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter numberOfLives;
    private ScoreTrackingListener scoreTracker;
    private Paddle paddle;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;
    private LevelInformation currentLevel;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 200;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BALL_START_X = 400;
    private static final int BALL_START_Y = 569;
    private static final int BALLS_SIZE = 5;
    private static final int LEFT_BORDER = 10;
    private static final int RIGHT_BORDER = 790;
    private static final int START_OF_COUNTER = 0;
    private static final int BROKE_ALL_BLOCKS_SCORE = 100;
    private static final int DECREASE_LIVES = 1;
    private static final int NUM_OF_SECONDS = 2000;
    private static final int COUNT_DOWN_FROM = 3;


    /**
     * Instantiates a new Game level.
     *
     * @param level          the level
     * @param keyboardSensor the keyboard sensor
     * @param animation      the animation
     * @param lives          the lives
     * @param sc             the sc
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboardSensor, AnimationRunner animation, Counter lives,
                     Counter sc) {
        this.currentLevel = level;
        this.keyboard = keyboardSensor;
        this.runner = animation;
        this.numberOfLives = lives;
        this.score = sc;
    }

    /**
     * The method adds a collidable to the collidable list.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * The method adds sprite to the sprites collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }


    /**
     * The method Initializes a new game: creates the Blocks and sprites.Ball
     * and collidable.collidable.Paddle and adds them to the game.
     */
    public void initialize() {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.keyboard = this.runner.getGui().getKeyboardSensor();
        this.blockCounter = new Counter(START_OF_COUNTER);
        this.ballCounter = new Counter(START_OF_COUNTER);
        this.blockRemover = new BlockRemover(this, this.blockCounter);
        this.ballRemover = new BallRemover(this, this.ballCounter);
        //track our score - see how much we are getting
        this.scoreTracker = new ScoreTrackingListener(this.score);
        //write the score to the screen
        this.scoreIndicator = new ScoreIndicator(this.score);
        this.livesIndicator = new LivesIndicator(this.numberOfLives);
        LevelName currentLevelName = new LevelName(this.currentLevel.levelName());
        currentLevelName.addToGame(this);
        sprites.addSprite(currentLevel.getBackground());
        //create the border blocks
        Point point1 = new Point(0, 20);
        Point point2 = new Point(0,  20);
        Point point3 = new Point(790, 20);
        Point point4 = new Point(0, 600);
        Point point5 = new Point(0, 0);
        //left
        Rectangle rectangle1 = new Rectangle(point1, 10, 600);
        //top
        Rectangle rectangle2 = new Rectangle(point2, 800, 20);
        //right
        Rectangle rectangle3 = new Rectangle(point3, 10, 800);
        Rectangle rectangle4 = new Rectangle(point4, 840, 5);
        Rectangle rectangle5 = new Rectangle(point5, 800, 20);
        Block infoBar = new Block(rectangle5, 0, Color.WHITE);
        //left border
        Block leftBorder = new Block(rectangle1, 0, Color.GRAY);
        //top border
        Block topBorder = new Block(rectangle2, 0, Color.GRAY);
        //right border
        Block rightBorder = new Block(rectangle3, 0, Color.GRAY);
        //death block
        Block deathBlock = new Block(rectangle4, 0, Color.GRAY);
        leftBorder.addToGame(this);
        topBorder.addToGame(this);
        rightBorder.addToGame(this);
        deathBlock.addToGame(this);
        infoBar.addToGame(this);
        deathBlock.addHitListener(this.ballRemover);
        //add the score indicator to the list of sprites
        sprites.addSprite(scoreIndicator);
        //add the lives indicator to the list of sprites
        sprites.addSprite(livesIndicator);
        currentLevelName.addToGame(this);
        for (Block b : currentLevel.blocks()) {
            b.addToGame(this);
            b.addHitListener(this.blockRemover);
            blockCounter.increase(1);
            b.addHitListener(scoreTracker);
        }

    }

    /**
     * The method Runs a turn.
     */
    public void playOneTurn() {
        this.createBallsAndPaddle();
        this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_DOWN_FROM, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
        }

    /**
     * The method Runs the game.
     */
    public void run() {
        if (this.numberOfLives.getValue() > 0) {
            playOneTurn();
            //this.numberOfLives.decrease(DECREASE_LIVES);
            this.run();
        } else {
            this.getGui().close();
        }
    }

    /**
     * The method Rremoves a collidable.
     *
     * @param c the collidable we want to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * The method Rremoves a sprite.
     *
     * @param s the sprite we want to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * The method gets the GUI.
     *
     * @return gui the gui
     */
    public GUI getGui() {
        return this.runner.getGui();
    }

    /**
     * The method gets the game environment.
     *
     * @return environment the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * The method gets the keyboard.
     *
     * @return the keyboard
     */
    public biuoop.KeyboardSensor getKeyboard() {
        return this.runner.getGui().getKeyboardSensor();
    }

    /**
     * Main method to run the game.
     *
     * @param args ignored.
     */
    public void main(String[] args) {
        GameLevel game = new GameLevel(this.currentLevel, this.keyboard, this.runner, this.numberOfLives, this.score);
        game.initialize();
        game.run();
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(BROKE_ALL_BLOCKS_SCORE);
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.sprites.removeSprite(paddle);
            this.removeCollidable(paddle);
            this.numberOfLives.decrease(DECREASE_LIVES);
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
    }

    /**
     * Create balls and paddle.
     */
    public void createBallsAndPaddle() {
        for (int i = 0; i < this.currentLevel.numberOfBalls(); i++) {
            Ball ball = new Ball(BALL_START_X, BALL_START_Y, BALLS_SIZE, Color.white, this.environment);
            ball.setVelocity(this.currentLevel.initialBallVelocities().get(i));
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
        paddle = new Paddle(this.currentLevel.paddleWidth(), PADDLE_HEIGHT, LEFT_BORDER, RIGHT_BORDER,
                currentLevel.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * Gets block counter.
     *
     * @return the block counter
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * Gets number of lives.
     *
     * @return the number of lives
     */
    public Counter getNumberOfLives() {
        return this.numberOfLives;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }
}