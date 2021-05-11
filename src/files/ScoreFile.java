package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * create read and change the score file.
 */
public class ScoreFile {
    private int score;
    private String fileName;

    /**
     * constructor with file name, path and score.
     *
     * @param fileName is the file name.
     * @param score    is the score.
     */
    public ScoreFile(String fileName, int score) {
        this.fileName = fileName;
        this.score = score;
    }

    /**
     * constructor with file name.
     *
     * @param fileName is the file name.
     */
    public ScoreFile(String fileName) {
        this.fileName = fileName;
    }

    /**
     * create the score file, or read and change the file.
     */
    public void createScoreFile() {
        FileWriter myWriter = null;
        Scanner myReader = null;
        try {
            File file = new File(fileName);
            // if we create a new file
            if (file.createNewFile()) {
                myWriter = new FileWriter(fileName);
                myWriter.write("The highest score so far is: " + score);
            } else {
                myReader = new Scanner(file);
                int x;
                String data;
                data = myReader.nextLine();
                int start = data.lastIndexOf(" ") + 1;
                x = Integer.parseInt(data.substring(start));
                if (x < score) {
                    myWriter = new FileWriter(fileName);
                    myWriter.write("The highest score so far is: " + score);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (myReader != null) {
                    myReader.close();
                }
                if (myWriter != null) {
                    myWriter.close();
                }
            } catch (IOException e) {
                System.out.println(" Failed closing the file !");
            }

        }

    }

    /**
     * get the high score from the file.
     *
     * @return the high score from the file.
     */
    public int getScoreFromFile() {
        Scanner myReader = null;
        int x;
        try {
            File file = new File(fileName);
            myReader = new Scanner(file);
            String data;
            data = myReader.nextLine();
            int start = data.lastIndexOf(" ") + 1;
            x = Integer.parseInt(data.substring(start));
            myReader.close();
        } catch (FileNotFoundException e) {
            x = 0;
        }
        return x;

    }
}
