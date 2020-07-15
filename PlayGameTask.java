package game;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import counter.Counter;
import levels.LevelInformation;
import menu.Task;
import scores.HighScoresTable;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Play game task.
 *
 * @param <T> the type parameter
 */
public class PlayGameTask<T> implements Task<T> {
    private HighScoresTable scores;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private File file;
    private List<LevelInformation> listOflevels;

    /**
     * Instantiates a new Play game task.
     *
     * @param table             the table
     * @param ar                the ar
     * @param ks                the ks
     * @param file              the file
     * @param levelInformations the level informations
     */
    public PlayGameTask(HighScoresTable table, AnimationRunner ar,
                        KeyboardSensor ks, File file, List<LevelInformation> levelInformations) {
        this.scores = table;
        this.ar = ar;
        this.ks = ks;
        this.file = file;
        this.listOflevels = levelInformations;
    }

    /**
     * Run t.
     *
     * @return the t
     */
    @Override
    public T run() {
        Counter score = new Counter(0);
        Counter lives = new Counter(7);
        GameFlow gameFlow = new GameFlow(ar, ks, score, lives, file);
        try {
            gameFlow.runLevels(listOflevels);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
