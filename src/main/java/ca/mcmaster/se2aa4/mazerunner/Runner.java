package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger logger = LogManager.getLogger();
    private int x, y;
    private char direction;
    private Maze maze;


    public Runner(Maze maze) {
        this.maze = maze;
        this.x = maze.getEntryCoords()[0];
        this.y = maze.getEntryCoords()[1];
        this.direction = 'E';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void moveForward() {
        switch (direction) {
            case 'E' -> y++;
            case 'W' -> y--;
            case 'N' -> x--;
            case 'S' -> x++;
        }
    }

    private void turnRight() {
        switch (direction) {
            case 'E' -> direction = 'S';
            case 'W' -> direction = 'N';
            case 'N' -> direction = 'E';
            case 'S' -> direction = 'W';
        }
    }

    private void turnLeft() {
        switch (direction) {
            case 'E' -> direction = 'N';
            case 'W' -> direction = 'S';
            case 'N' -> direction = 'W';
            case 'S' -> direction = 'E';
        }
    }

    private void turnAround() {
        switch (direction) {
            case 'E' -> direction = 'W';
            case 'W' -> direction = 'E';
            case 'N' -> direction = 'S';
            case 'S' -> direction = 'N';
        }
    }

    // Validates path in canonical form 
    public void validatePath(String pathSequence) {
        OUTER:
        for (int i = 0; i < pathSequence.length(); i++) {
            char move = pathSequence.charAt(i);
            
            switch (move) {
                case 'F' -> {
                    if (!maze.canMoveForward(x, y, direction)) {
                        logger.info("Can't move forward");
                        break OUTER;
                    } else {
                        moveForward();
                        logger.info("Moving forward: " + x + " " + y);
                    }
                }
                case 'R' -> {
                    turnRight();
                    logger.info("Turning Right: " + x + " " + y);
                }
                case 'L' -> {
                    turnLeft();
                    logger.info("Turning Left: " + x + " " + y);
                }
                case ' ' -> {
                    continue;
                }
                default -> logger.error("Invalid Symbol");
            }
        }
 
        if (x == maze.getExitCoords()[0] && y == maze.getExitCoords()[1]) System.out.println("correct path");
        else System.out.println("incorrect path");
     
    }

}
