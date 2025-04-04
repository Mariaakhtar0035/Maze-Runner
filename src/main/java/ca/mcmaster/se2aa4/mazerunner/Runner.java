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


    // Getters/Setters for the runner's current state
    public char getDirection() { return direction; }
    public void setDirection(char direction) {this.direction = direction;}
    public void setPosition(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getEndX() { return endX; }
    public int getEndY() { return endY; }


    public int[] getNextPosition(char direction) {
        int nextX = x, nextY = y;

        if (direction == 'E') {
            nextY++;
        } else if (direction == 'W') {
            nextY--;
        } else if (direction == 'N') {
            nextX--;
        } else if (direction == 'S') {
            nextX++;
        }

        return new int[]{nextX, nextY};
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < maze.getRows() && 
               y >= 0 && y < maze.getCols() && 
               maze.isPass(x, y);
    }
}