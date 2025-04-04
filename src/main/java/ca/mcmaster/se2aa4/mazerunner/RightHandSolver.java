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

    protected final Command moveForward;
    protected final Command turnRight;
    protected final Command turnLeft;
    

    public RightHandSolver(Maze maze, Path path, Runner runner) {
        this.path = path;
        this.runner = runner;

        this.moveForward = new MoveForward(runner);
        this.turnRight = new TurnRight(runner);
        this.turnLeft = new TurnLeft(runner);
    }


    /**
     * Computes the path through the maze using the right-hand rule algorithm.
     * The runner follows the right wall, turning right when possible, moving forward otherwise,
     * and turning left or around as a last resort.
     */
    @Override
    public void computePath() {
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


    private boolean isAtExit() {
        return runner.getX() == runner.getEndX() && runner.getY() == runner.getEndY();
    }

    private void executeCommand(Command command, char instruction) {
        if (command.execute()) {
            path.addInstruction(instruction);
        }
    }  
}

