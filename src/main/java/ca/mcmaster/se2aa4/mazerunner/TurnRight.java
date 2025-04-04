package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TurnRight implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final Runner runner;

    public TurnRight(Runner runner) {
        this.runner = runner;
    }

    @Override
    // Checks if the runner can turn right without hitting a wall
    public boolean canExecute() {
        char newDirection = getNextDirection();
        int[] nextPos = runner.getNextPosition(newDirection);
        return runner.isValidPosition(nextPos[0], nextPos[1]);
    }

    @Override
    // Executes the right turn command, updating the player's direction
    public boolean execute() {
        char newDirection = getNextDirection();
        runner.setDirection(newDirection);
        logger.info("Turning Right: " + runner.getX() + " " + runner.getY());
        return true;
    }

    // Calculates the new direction after a right turn
    public char getNextDirection() {
        char currentDirection = runner.getDirection();
        char newDirection;
        
        if (currentDirection == 'N') {
            newDirection = 'E';
        } else if (currentDirection == 'E') {
            newDirection = 'S';
        } else if (currentDirection == 'S') {
            newDirection = 'W';
        } else {
            newDirection = 'N';
        }

        return newDirection;
    }
}
