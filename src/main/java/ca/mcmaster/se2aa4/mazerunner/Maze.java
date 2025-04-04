/**
 * Maze class represents a maze loaded from a file.
 * It provides functionality to load the maze, find entry and exit points, and check if a cell is passable.
 */
package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public boolean intializeMaze(String mazeFile) {
        loadMaze(mazeFile);
        findEntry();
        findExit();

        // Log an error if the entry or exit points are not found
        if (!findEntry() || !findExit()) {
            logger.error("Maze entry or exit not found.");
            return false;
        }

        return true;
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
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            logger.error("Attempted to access out-of-bounds index: (" + x + ", " + y + ")");
            return false; 
        }
        return maze[x][y] == ' ';
    }


    /**
     * Loads the maze from a file into a 2D array.
     *
     * @param mazeFile Path to the maze file.
     */
    private void loadMaze(String mazeFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(mazeFile));

            String line;

            // load all rows and determine the maximum column size (longest row)
            ArrayList<String> lines = new ArrayList<>();
            int maxCols = 0;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
                maxCols = Math.max(maxCols, line.length());
            }

            rows = lines.size();
            cols = maxCols;
            maze = new char[rows][cols];

            // Fill maze with padded rows
            for (int i = 0; i < rows; i++) {
                String currentLine = lines.get(i);
                // Pad the current row with spaces if it's shorter than the longest row
                for (int j = 0; j < cols; j++) {
                    if (j < currentLine.length()) {
                        maze[i][j] = currentLine.charAt(j);
                    } else {
                        maze[i][j] = ' '; 
                    }
                }
            }

            logger.info("Maze loaded: " + rows + "x" + cols);

        } catch (IOException e) {
            logger.error("Error loading maze", e);
        } 
}
    

    /**
     * Finds the entry point of the maze on the west side (first column).
     */
    protected boolean findEntry() {
        for (int i = 0; i < rows; i++) {
            if (maze[i][0] == ' ') {
                startX = i;
                startY = 0;
                return true;
            }
        }

        return false;
    }

    /**
     * Finds the exit point of the maze on the east side (last column).
     */
    protected boolean findExit() {
        for (int i = rows - 1; i >= 0; i--) {
            if (maze[i][cols - 1] == ' ') {
                endX = i;
                endY = cols - 1;
                return true;
            }
        }

        return false;
    }
}