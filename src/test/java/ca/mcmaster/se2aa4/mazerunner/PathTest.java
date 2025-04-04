package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PathTest {
    @Test
    // Test the Canonical Path Formatting 
    void testPathCanonicalFormat() {
        Path path = new Path();
        path.addInstruction('F');
        path.addInstruction('F');
        path.addInstruction('R');
        path.addInstruction('F');

        assertEquals("FF R F", path.outputPathCanonical());
    }

    @Test
    // Test the Factorized Path Formatting
    void testPathFactorizedFormat() {
        Path path = new Path();
        path.addInstruction('F');
        path.addInstruction('F');
        path.addInstruction('R');
        path.addInstruction('F');
        path.addInstruction('F');
        path.addInstruction('F');

        assertEquals("2F R 3F", path.outputPathFactorized());
    }

    @Test
    // Test path validation for a correct solution
    void testCorrectPath() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/straight.maz.txt");
        Runner runner = new Runner(maze);
        PathValidator validator = new PathValidator(runner);
        assertEquals("Correct path", validator.validatePath("4F"));
    }

    @Test
    // Test path validation for an incorrect solution
    void testIncorrectPath() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/small.maz.txt");
        Runner runner = new Runner(maze);
        PathValidator validator = new PathValidator(runner);
        assertEquals("Incorrect path", validator.validatePath("4F"));
    }

    /**
     * Integration test for the RightHandSolver algorithm.
     * 1. Generates a path using the right-hand rule.
     * 2. Validates that the computed path actually solves the maze.
     */
    @Test
    void testRightHandSolver() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/small.maz.txt");

        Path path = new Path();
        Runner runner = new Runner(maze);
        RightHandSolver rightHandSolver = new RightHandSolver(maze, path, runner);
        rightHandSolver.computePath();
        String factorizedPath = path.outputPathFactorized();

        Runner runner2 = new Runner(maze);

        PathValidator pathValidator = new PathValidator(runner2);

        assertEquals("Correct path", pathValidator.validatePath(factorizedPath));
    }

}
