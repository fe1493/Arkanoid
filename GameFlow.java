package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import counter.Counter;
import levels.LevelInformation;
import scores.HighScoresAnimation;
import scores.HighScoresTable;
import scores.ScoreInfo;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter lives;
    private Counter score;
    private File highScoresFile;
    private HighScoresTable highScores;
    private List levels;


    /**
     * Instantiates a new Game flow.
     *
     * @param ar             the ar
     * @param ks             the ks
     * @param sc             the sc
     * @param liv            the liv
     * @param highScoresFile the highscores
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter sc, Counter liv, File highScoresFile) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = sc;
        this.lives = liv;
        this.highScoresFile = highScoresFile;
        this.highScores = HighScoresTable.loadFromFile(highScoresFile);
    }

    /**
     * Instantiates a new Game flow.
     *
     * @param ar                the ar
     * @param ks                the ks
     * @param sc                the sc
     * @param liv               the liv
     * @param highScoresFile    the high scores file
     * @param levelInformations the level informations
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter sc, Counter liv, File highScoresFile,
                    List<LevelInformation> levelInformations) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = sc;
        this.lives = liv;
        this.highScoresFile = highScoresFile;
        this.highScores = HighScoresTable.loadFromFile(highScoresFile);
        this.levels = levelInformations;
    }

    /**
     * Run levels.
     *
     * @param lev the levels
     * @throws IOException throw exception
     */
    public void runLevels(List<LevelInformation> lev) throws IOException {
        HighScoresTable highScoresTable = HighScoresTable.loadFromFile(this.highScoresFile);
        try {
            highScoresTable.save(this.highScoresFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (LevelInformation levelInfo : lev) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.lives,
                    this.score);
            level.initialize();
            while ((level.getNumberOfLives().getValue() > 0) && (level.getBlockCounter().getValue() > 0)) {
                level.playOneTurn();
            }

        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, keyboardSensor.SPACE_KEY, (new
                EndScreen(this.keyboardSensor, score, lives))));
        if (highScoresTable.getRank(this.score.getValue()) < this.highScores.size() && this.score.getValue() > 0) {
            DialogManager dialog = animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            System.out.println(name);
            highScoresTable.add(new ScoreInfo(name, this.score.getValue()));
            try {
                highScoresTable.save(this.highScoresFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            HighScoresAnimation highScoresAnimation = new HighScoresAnimation(highScoresTable);
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", highScoresAnimation));


    }
}