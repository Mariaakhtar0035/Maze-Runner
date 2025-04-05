/**
 * RightHandSolver class implements the MazeSolver interface.
 * This class uses the right-hand rule algorithm to navigate through the maze.
 */
package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver extends AbstractMazeSolver {
    private static final Logger logger = LogManager.getLogger();

    public RightHandSolver(Maze maze, Path path, Runner runner) {
        super(maze, path, runner);
    }


    /**
     * Computes the path through the maze using the right-hand rule algorithm.
     * The runner follows the right wall, turning right when possible, moving forward otherwise,
     * and turning left or around as a last resort.
     */
    @Override
    public void determineNextMove() {
        while (!isAtExit()) {
            if (turnRight.canExecute()) {
                executeCommand(turnRight, 'R');
                executeCommand(moveForward, 'F');
            } else if (moveForward.canExecute()) {
                executeCommand(moveForward, 'F');
            } else if (turnLeft.canExecute()) {
                executeCommand(turnLeft, 'L');
                executeCommand(moveForward, 'F');
            } else {
                executeCommand(turnRight, 'R'); 
                executeCommand(turnRight, 'R'); // 180-degree turn
            }
        }

        logger.info("Runner has reached the exit: " + runner.getX() + " " + runner.getY());
    }
  
}

