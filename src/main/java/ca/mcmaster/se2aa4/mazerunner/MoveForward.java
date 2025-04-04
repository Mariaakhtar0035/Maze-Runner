package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoveForward implements Command{
    private static final Logger logger = LogManager.getLogger();
    private final Runner runner;

    public MoveForward(Runner runner) {
        this.runner = runner;
    }

    @Override
    // Checks if the runner can move forward without hitting a wall
    public boolean canExecute() {
        int[] nextPos = runner.getNextPosition(runner.getDirection());
        return runner.isValidPosition(nextPos[0], nextPos[1]);
    }

    @Override
    // Executes the move forward command, updating the runner's position
    public boolean execute() {
        int[] nextPos = runner.getNextPosition(runner.getDirection());
        runner.setPosition(nextPos[0], nextPos[1]);
        logger.info("Moving forward: " + runner.getX() + " " + runner.getY());
        return true;
    }

}
