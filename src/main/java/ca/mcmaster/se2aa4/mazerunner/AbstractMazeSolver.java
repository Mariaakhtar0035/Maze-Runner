package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Abstract base class for maze solving algorithms.
 * Provides common functionality for path computation while allowing
 * subclasses to implement specific movement strategies.
 */
public abstract class AbstractMazeSolver implements MazeSolver{
    private static final Logger logger = LogManager.getLogger();
    protected Path path;
    protected Runner runner;
    protected Maze maze;

    protected final Command moveForward;
    protected final Command turnRight;
    protected final Command turnLeft;


    public AbstractMazeSolver(Maze maze, Path path, Runner runner) {
        this.maze = maze;
        this.path = path;
        this.runner = runner;

        this.moveForward = new MoveForward(runner);
        this.turnRight = new TurnRight(runner);
        this.turnLeft = new TurnLeft(runner);
    }

    @Override
    public final void computePath() {
        logger.info("Starting path computation");
        initialize(); // Hook for subclass initialization
        
        while (!isAtExit()) {
            determineNextMove();
        }
        
        finalizePath(); // Hook for cleanup/optimization
        logger.info("Runner has reached the exit: " + runner.getX() + " " + runner.getY());
    }

    protected abstract void determineNextMove();
    protected void initialize() {} // Optional override
    protected void finalizePath() {} // Optional override
    
    
    protected boolean isAtExit() {
        return runner.getX() == runner.getEndX() && runner.getY() == runner.getEndY();
    }
    
    protected void executeCommand(Command command, char instruction) {
        if (command.execute()) {
            path.addInstruction(instruction);
        }
    }
}
