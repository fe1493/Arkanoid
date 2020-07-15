
package scores;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * The type High scores table.
 */
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> highScoresList = new ArrayList<>();


    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.size = size;
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        // if get rank with this score is not 0
        int x = this.getRank(score.getScore());
        if (x != 0) {
            highScoresList.add(x - 1, score);
        }
        if (this.highScoresList.size() > this.size()) {
            this.highScoresList.remove(this.getHighScores().size() - 1);
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */

    public List<ScoreInfo> getHighScores() {
        return this.highScoresList;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */

    public int getRank(int score) {
        int i;
        int rank = 0;
        if (this.highScoresList.isEmpty()) {
            return 1;
        }
        for (i = 0; i < this.highScoresList.size(); i++) {
            if (score > this.highScoresList.get(i).getScore()) {
                rank = i + 1;
                return rank;
            }
            rank = this.highScoresList.size() + 1;
        }
        return rank;
    }

    /**
     * Clear.
     */
    public void clear() {
        highScoresList.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        this.clear();
        ScoreInfo scoreInfo;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            while ((scoreInfo = (ScoreInfo) objectInputStream.readObject()) != null) {
                add(scoreInfo);
            }

        } catch (EOFException e) { // end of file
            return;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            return;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        ObjectOutputStream highScoresTableOutoutStream = null;
        try {
            highScoresTableOutoutStream = new ObjectOutputStream(new FileOutputStream(filename));
            for (ScoreInfo s : getHighScores()) {
                highScoresTableOutoutStream.writeObject(s);
            }
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (highScoresTableOutoutStream != null) {
                    highScoresTableOutoutStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */

    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable hst = new HighScoresTable(6);
        try {
            hst.load(filename);
        } catch (IOException e) {
            hst.clear();
            e.printStackTrace();
        }
        return hst;
    }
}
