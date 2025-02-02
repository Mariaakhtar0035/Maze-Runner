/**
 * RightHandSolver class implements the MazeSolver interface.
 * This class uses the right-hand rule algorithm to navigate through the maze.
 */
package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private Path path;
    private Runner runner;

    public RightHandSolver(Maze maze, Path path, Runner runner) {
        this.path = path;
        this.runner = runner;
    }


    /**
     * Computes the path through the maze using the right-hand rule algorithm.
     * The runner follows the right wall, turning right when possible, moving forward otherwise,
     * and turning left or around as a last resort.
     */
    @Override
    public void computePath() {
        while (!isAtExit()) {
            if (canTurnRight()) {
                turnRight();
            } else if (canMoveForward()) {
                moveForward();
            } else if (canTurnLeft()) {
                turnLeft();
            } else {
                turnAround();
            }
        }

        logger.info("Runner has reached the exit: " + runner.getX() + " " + runner.getY());
    }


    /**
     * Checks if the runner has reached the exit, can turn right or left, or move forward in the current position.
     *
     * @return True if the runner can perform the action, false otherwise.
     */
    private boolean isAtExit() {
        return runner.getX() == runner.getEndX() && runner.getY() == runner.getEndY();
    }

    private boolean canTurnRight() {
        return runner.canTurnRight(runner.getX(), runner.getY(), runner.getDirection());
    }

    private boolean canMoveForward() {
        return runner.canMoveForward(runner.getX(), runner.getY(), runner.getDirection());
    }

    private boolean canTurnLeft() {
        return runner.canTurnLeft(runner.getX(), runner.getY(), runner.getDirection());
    }



    /**
     * Turns the runner left or right, or moves the runner forward and updates the path.
     */
    private void turnLeft() {
        logger.info("Turning Left: " + runner.getX() + " " + runner.getY());
        runner.turnLeft();
        path.addInstruction('L');
        moveForward();
    }

    private void turnRight() {
        logger.info("Turning Right: " + runner.getX() + " " + runner.getY());
        runner.turnRight();
        path.addInstruction('R');
        moveForward();
    }

    private void moveForward() {
        if (canMoveForward()) {
            logger.info("Moving forward: " + runner.getX() + " " + runner.getY());
            runner.moveForward();
            path.addInstruction('F');
        }
    }

    /**
     * Turns the runner around (180 degrees) by simulating two right turns and updates the path.
     */
    private void turnAround() {
        logger.info("Turning Around: " + runner.getX() + " " + runner.getY());
        runner.turnRight();
        path.addInstruction('R');
        runner.turnRight();
        path.addInstruction('R');
    }
}

