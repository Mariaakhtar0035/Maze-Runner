/**
 * Maze class represents a maze loaded from a file.
 * It provides functionality to load the maze, find entry and exit points, and check if a cell is passable.
 */
package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Maze {
    private static final Logger logger = LogManager.getLogger();

    private char[][] maze;
    private int rows;
    private int cols;
    private int startX = -1;
    private int startY = -1;
    private int endX;
    private int endY;

    public void intializeMaze(String mazeFile) {
        loadMaze(mazeFile);
        findEntry();
        findExit();

        // Log an error if the entry or exit points are not found
        if (startX == -1 || endX == -1) {
            logger.error("Maze entry or exit not found.");
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[] getEntryCoords() {
        return new int[]{startX, startY};
    }

    public int[] getExitCoords() {
        return new int[]{endX, endY};
    }

    public boolean isPass(int x, int y) {
        return maze[x][y] == ' ';
    }


    /**
     * Loads the maze from a file into a 2D array.
     *
     * @param mazeFile Path to the maze file.
     */
    private void loadMaze(String mazeFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mazeFile));

            // Read the first line to get the # of columns 
            String line = reader.readLine();

            if (line == null) {
                logger.error("Maze file is empty.");
            }

            // Start rows at 1 since the first line has already been read
            rows = 1;
            cols = line.length();
            
            // Iterate through the rest of the lines to get the # of rows
            while ((reader.readLine()) != null) {
                rows++;
            }

            reader.close(); 
            
            reader = new BufferedReader(new FileReader(mazeFile));
            maze = new char[rows][cols];
            int currentRow = 0;

            // Read each line and fill the maze array
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < cols; col++) {
                    maze[currentRow][col] = line.charAt(col);
                }
                currentRow++;
            }

            reader.close(); 

        } catch(IOException e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        
    }

    /**
     * Finds the entry point of the maze on the west side (first column).
     */
    private void findEntry() {
        for (int i = 0; i < rows; i++) {
            if (maze[i][0] == ' ') {
                startX = i;
                startY = 0;
                break;
            }
        }
    }

    /**
     * Finds the exit point of the maze on the east side (last column).
     */
    private void findExit() {
        for (int i = rows - 1; i >= 0; i--) {
            if (maze[i][cols - 1] == ' ') {
                endX = i;
                endY = cols - 1;
                break;
            }
        }
    }
}