package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Maze {
    private static final Logger logger = LogManager.getLogger();

    private char[][] maze;
    private int rows;
    private int cols;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public void intializeMaze(String mazeFile) {
        loadMaze(mazeFile);
        findEntry();
        findExit();

    }

    private void loadMaze(String mazeFile) {
        try {
            logger.info("Reading the maze from file " + mazeFile);
            BufferedReader reader = new BufferedReader(new FileReader(mazeFile));

            // Read the first line to get the # of columns 
            String line = reader.readLine();

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
            logger.info("Maze loaded successfully");

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        
    }

    // Helper method for debugging 
    private void printMaze() {
        // Print each char in the file
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j]);
            }
            // Print a newline after each row
            System.out.println();
        }

    }

    // Finds entry point on west end 
    private void findEntry() {
        for (int i = 0; i < rows; i++) {
            if (maze[i][0] == ' ') {
                startX = i;
                startY = 0;
                break;
            }
        }
    }

    // Finds exit point on east end 
    private void findExit() {
        for (int i = rows - 1; i >= 0; i--) {
            if (maze[i][cols - 1] == ' ') {
                endX = i;
                endY = cols - 1;
                break;
            }
        }
    }

    public int[] getEntryCoords() {
        return new int[]{startX, startY};
    }

    public int[] getExitCoords() {
        return new int[]{endX, endY};
    }

    // Checks to see if the runner can move forward (no wall ahead)
    public boolean canMoveForward(int x, int y, char direction) {
        int nextX = x;
        int nextY = y;

        switch (direction) {
            case 'E' -> nextY++;
            case 'W' -> nextY--;
            case 'N' -> nextX--;
            case 'S' -> nextX++;
        }
        return nextX >= 0 && nextX < rows &&
           nextY >= 0 && nextY < cols &&
           maze[nextX][nextY] == ' ';
            
    }

    public boolean canTurnRight(int x, int y, char direction) {
        // Simulate a right turn 
        char newDirection = switch (direction) {
            case 'N' -> 'E';
            case 'E' -> 'S';
            case 'S' -> 'W';
            case 'W' -> 'N';
            default -> direction; 
        };

        return canMoveForward(x, y, newDirection);
    }

    public boolean canTurnLeft(int x, int y, char direction) {
        // Simulate a left turn
        char newDirection = switch (direction) {
            case 'E' -> 'N';
            case 'W' -> 'S';
            case 'N' -> 'W';
            case 'S' -> 'E';
            default -> direction; 
        };

        return canMoveForward(x, y, newDirection);
    }
}

