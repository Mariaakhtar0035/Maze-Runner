package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger logger = LogManager.getLogger();
    private int x, y;
    private char direction;

    public Runner(Maze maze) {
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
}
