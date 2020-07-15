import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;

import levelreader.LevelReaderForSubMenu;
import levelreader.LevelSpecificationReader;
import levelreader.SetFileFormat;
import levels.LevelInformation;
import menu.MenuAnimation;
import game.PlayGameTask;
import menu.Task;
import scores.HighScoresAnimation;
import scores.HighScoresTable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


/**
 * This class the Ass7Game class.
 */
public class Ass7Game {
    private AnimationRunner runner = new AnimationRunner();
    private File file = new File("high scores.ser");
    private HighScoresTable highScoresTable = new HighScoresTable(6);
    private Animation highScoresAnimation;
    private String levelPath = "level_sets.txt";

    {
        try {
            highScoresAnimation = new HighScoresAnimation(getLoadedHighScoresTable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<LevelInformation> listOflevels = new ArrayList<>();

    private boolean stop;
    private Task<Void> showHiScoresTask = new Task<Void>() {
        @Override
        public Void run() {
            runner.run(new KeyPressStoppableAnimation(runner.getGui().getKeyboardSensor(),
                    "space", highScoresAnimation));
            return null;

        }
    };

    private Task<Void> quitTask = new Task<Void>() {

        @Override
        public Void run() {
            System.exit(1);
            return null;
        }
    };


    /**
     * Main method to run the game.
     *
     * @param args ignored.
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        Ass7Game game = new Ass7Game();
        LevelReaderForSubMenu levelReaderForSubMenu = new LevelReaderForSubMenu();
        List<SetFileFormat> setFileFormatArrayList = new ArrayList<>();
        final String path = game.levelPath;
        BufferedReader reader = null;
        InputStream input;
        try {
            if (args.length > 0) {
                input = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
            } else {
                input = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
            }
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (path.equals("level_sets.txt")) {
            // Interpreting the file given.
            setFileFormatArrayList = levelReaderForSubMenu.readLevels(reader);
            // In case the file is not given.
        } else {
            game.listOflevels = new LevelSpecificationReader().fromReader(reader);
        }
        MenuAnimation<Task<Void>> subMenu = new MenuAnimation<>(game.runner.getGui().getKeyboardSensor(),
                "Levels", game.runner);
        for (int i = 0; i < setFileFormatArrayList.size(); i++) {
            subMenu.addSelection(setFileFormatArrayList.get(i).getKey(), setFileFormatArrayList.get(i).getName(),
                    new PlayGameTask<>(game.highScoresTable, game.runner, game.runner.getGui().getKeyboardSensor(),
                            game.file, setFileFormatArrayList.get(i).getLevelList()), null);
        }

        if (!game.levelPath.equals("level_sets.txt")) {
            subMenu = null;
        }
        MenuAnimation<Task<Void>> menu = new MenuAnimation<>(game.runner.getGui().getKeyboardSensor(),
                "Levels", game.runner);

        menu.addSelection("h", "Show Hi Scores", game.showHiScoresTask, null);
        menu.addSubMenu("s", "Start Game", new PlayGameTask<>(game.highScoresTable,
                game.runner, game.runner.getGui().getKeyboardSensor(), game.file, game.listOflevels), subMenu);
        menu.addSelection("q", "Quit Game", game.quitTask, null);
        game.stop = menu.shouldStop();
        while (true) {
            game.runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
            (menu).setStop(false);
        }
    }

    /**
     * Gets loaded high scores table.
     *
     * @return the loaded high scores table
     * @throws IOException the io exception
     */
    public HighScoresTable getLoadedHighScoresTable()throws IOException {
        this.highScoresTable.load(this.file);
        return highScoresTable;

    }

}