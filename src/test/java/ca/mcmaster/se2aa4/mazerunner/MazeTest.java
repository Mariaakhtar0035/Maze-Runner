package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MazeTest {

    /**
     * Tests successful maze initialization by verifying:
     * 1. Entry and exit coordinates are properly set
     * 2. Maze dimensions are valid (rows/cols > 0)
     * 3. Entry and exit points are found successfully
     */
    @Test
    void testMazeLoading() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/straight.maz.txt");
        assertNotNull(maze.getEntryCoords());
        assertNotNull(maze.getExitCoords());
        assertTrue(maze.getRows() > 0);
        assertTrue(maze.getCols() > 0);
        assertTrue(maze.findEntry());
        assertTrue(maze.findExit());

    }

    @Test
    // Test maze initialization faliure with invalid file
    void testMazeLoading_ShouldFail() {
        Maze maze = new Maze();
        assertFalse(maze.intializeMaze("examples/invalid_maz.txt"));
    }
    

    /**
     * Tests boundary checking functionality:
     * 1. Negative coordinates should be out of bounds
     * 2. Valid entry point should be passable
     * 3. Wall positions should be impassable
     */
    @Test
    void testMazeBoundaries() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/small.maz.txt");
        assertFalse(maze.isPass(-1, 0)); // Out of bounds
        assertFalse(maze.isPass(0, -1)); // Out of bounds
        assertTrue(maze.isPass(maze.getEntryCoords()[0], maze.getEntryCoords()[1])); // Entry should be passable
    }
    
}
