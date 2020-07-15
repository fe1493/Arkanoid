package levelreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level reader for sub menu.
 */
public class LevelReaderForSubMenu {
        private List<SetFileFormat> setFileFormats;

    /**
     * readLevels() read the levels from BufferedReader.
     *
     * @param reader the BufferedReader.
     * @return List of SetFileFormat.
     */
    public List<SetFileFormat> readLevels(BufferedReader reader) {
            this.setFileFormats = new ArrayList<>();
            String tempLine;
            String[] line;
            int count = 1;
            try {
                tempLine = reader.readLine();
                SetFileFormat setFileFormat = null;
                while (tempLine != null) {
                    if (count % 2 == 1) {
                        line = tempLine.split(":");
                        setFileFormat = new SetFileFormat();
                        setFileFormat.addKeyAndName(line[0], line[1]);
                    } else if (count % 2 == 0) {
                        setFileFormat.addDefi(tempLine);
                        setFileFormats.add(setFileFormat);
                    }
                    count++;
                    tempLine = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this.setFileFormats;
        }

    /**
     * getSff().
     *
     * @return List of SetFileFormat.
     */
    public List<SetFileFormat> getSff() {
            return this.setFileFormats;
        }
    }