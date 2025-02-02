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

    public char getDirection() { return direction; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getEndX() { return endX; }
    public int getEndY() { return endY; }

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

    public boolean canMoveForward(int x, int y, char direction) {
        int nextX = x;
        int nextY = y;

        if (direction == 'E') {
            nextY++;
        } else if (direction == 'W') {
            nextY--;
        } else if (direction == 'N') {
            nextX--;
        } else if (direction == 'S') {
            nextX++;
        }

        return nextX >= 0 && nextX < maze.getRows() &&
               nextY >= 0 && nextY < maze.getCols() &&
               maze.isPass(nextX, nextY);
    }

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