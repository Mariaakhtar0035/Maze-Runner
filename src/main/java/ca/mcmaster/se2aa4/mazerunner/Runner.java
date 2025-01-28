package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger logger = LogManager.getLogger();
    private int x, y, endX, endY;
    private char direction;
    private Maze maze;
    private Path path;


    public Runner(Maze maze, Path path) {
        this.maze = maze;
        this.path = path;
        this.x = maze.getEntryCoords()[0];
        this.y = maze.getEntryCoords()[1];
        this.endX = maze.getExitCoords()[0];
        this.endY = maze.getExitCoords()[1];
        this.direction = 'E';
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return direction;
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

    public void turnRight() {
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

    // Validates path in canonical or factorized form 
    public void validatePath(String pathSequence) {
        int moveInt = 1;

        OUTER:
        for (int i = 0; i < pathSequence.length(); i++) {
            char move = pathSequence.charAt(i);

            if (Character.isDigit(move)) {
                moveInt = move - '0';
                continue;
            }

            if (move == 'F' || move == 'R' || move == 'L' || move == ' ') {
                for (int j = 0; j < moveInt; j++) {
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
                    }
                }

                moveInt = 1;
            }
        }
 
        if (x == maze.getExitCoords()[0] && y == maze.getExitCoords()[1]) System.out.println("correct path");
        else System.out.println("incorrect path");
     
    }

    // Generates a path using the right-hand algorithm
    public void computePath() {
        while (x != endX || y != endY) {
            if (maze.canTurnRight(x, y, direction)) {
                logger.info("Turning Right: " + x + " " + y);
                turnRight();
                path.addInstruction('R');
                if (maze.canMoveForward(x, y, direction)) {
                    moveForward();
                    path.addInstruction('F');
                }
            }
            else if (maze.canMoveForward(x, y, direction)) {
                logger.info("Moving forward: " + x + " " + y);
                moveForward();
                path.addInstruction('F');
            }
            else if (maze.canTurnLeft(x, y, direction)) {
                logger.info("Turning Left: " + x + " " + y);
                turnLeft();
                path.addInstruction('L');
                if (maze.canMoveForward(x, y, direction)) {
                    moveForward();
                    path.addInstruction('F');
                }
            }
            else {
                logger.info("Turning Around: " + x + " " + y);
                turnRight();
                path.addInstruction('R');
                turnRight();
                path.addInstruction('R');
            }

        }

        logger.info("Runner has reached the exit: " + x + " " + y);
    }

}
