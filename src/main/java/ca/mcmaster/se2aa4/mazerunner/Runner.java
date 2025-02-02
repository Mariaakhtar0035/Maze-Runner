/**
 * Runner class for navigating through a maze.
 * This class represents a runner that can move through the maze, turn left or right,
 * and check if it can move forward or turn in a given direction.
 */
package ca.mcmaster.se2aa4.mazerunner;


public class Runner {
    private int x, y, endX, endY;
    private char direction;
    private final Maze maze;


    public Runner(Maze maze) {
        this.maze = maze;
        this.x = maze.getEntryCoords()[0];
        this.y = maze.getEntryCoords()[1];
        this.endX = maze.getExitCoords()[0];
        this.endY = maze.getExitCoords()[1];
        this.direction = 'E';
    }


    // Getters for the runner's current state
    public char getDirection() { return direction; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getEndX() { return endX; }
    public int getEndY() { return endY; }


    /**
     * Moves the runner forward in the current direction.
     * Updates the runner's position based on the current direction.
     */
    public void moveForward() {
        if (direction == 'E') {
            y++;
        } else if (direction == 'W') {
            y--;
        } else if (direction == 'N') {
            x--;
        } else if (direction == 'S') {
            x++;
        }
    }

    /**
     * Turns the runner 90 degrees to the right.
     * Updates the runner's direction accordingly.
     */
    public void turnRight() {
        if (direction == 'E') {
            direction = 'S';
        } else if (direction == 'W') {
            direction = 'N';
        } else if (direction == 'N') {
            direction = 'E';
        } else if (direction == 'S') {
            direction = 'W';
        }
    }

    /**
     * Turns the runner 90 degrees to the left.
     * Updates the runner's direction accordingly.
     */
    public void turnLeft() {
        if (direction == 'E') {
            direction = 'N';
        } else if (direction == 'W') {
            direction = 'S';
        } else if (direction == 'N') {
            direction = 'W';
        } else if (direction == 'S') {
            direction = 'E';
        }
    }


    /**
     * Checks if the runner can move forward in the current direction.
     *
     * @param x The current X coordinate of the runner.
     * @param y The current Y coordinate of the runner.
     * @param direction The current direction the runner is facing.
     * @return True if the runner can move forward, false otherwise.
     */
    public boolean canMoveForward(int x, int y, char direction) {
        int nextX = x;
        int nextY = y;

        // Calculate the next position based on the direction
        if (direction == 'E') {
            nextY++;
        } else if (direction == 'W') {
            nextY--;
        } else if (direction == 'N') {
            nextX--;
        } else if (direction == 'S') {
            nextX++;
        }

        // Check if the next position is within the maze boundaries and is a passable cell
        return nextX >= 0 && nextX < maze.getRows() &&
               nextY >= 0 && nextY < maze.getCols() &&
               maze.isPass(nextX, nextY);
    }


    /**
     * Checks if the runner can turn right/left and move forward in the new direction.
     *
     * @param x The current X coordinate of the runner.
     * @param y The current Y coordinate of the runner.
     * @param direction The current direction the runner is facing.
     * @return True if the runner can turn right/left and move forward, false otherwise.
     */
    public boolean canTurnRight(int x, int y, char direction) {
        char newDirection = direction;
        if (direction == 'N') {
            newDirection = 'E';
        } else if (direction == 'E') {
            newDirection = 'S';
        } else if (direction == 'S') {
            newDirection = 'W';
        } else if (direction == 'W') {
            newDirection = 'N';
        }

        return canMoveForward(x, y, newDirection);
    }

    public boolean canTurnLeft(int x, int y, char direction) {
        char newDirection = direction;
        if (direction == 'E') {
            newDirection = 'N';
        } else if (direction == 'W') {
            newDirection = 'S';
        } else if (direction == 'N') {
            newDirection = 'W';
        } else if (direction == 'S') {
            newDirection = 'E';
        }

        return canMoveForward(x, y, newDirection);
    }

}