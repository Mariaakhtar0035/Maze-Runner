package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MovementTest {

    /**
     * Tests that the Runner initializes correctly with:
     * - Position at the maze's entry point
     * - Default direction facing East ('E')
     */
    @Test
    void testRunnerInitialization() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/small.maz.txt");
        Runner runner = new Runner(maze);
        assertEquals(maze.getEntryCoords()[0], runner.getX());
        assertEquals(maze.getEntryCoords()[1], runner.getY());
        assertEquals('E', runner.getDirection()); // Assuming initial direction is East
    }

    @Test
    // Test that moveForward() correctly updates the runner's position (eastbound)
    void testForwardMovementEast() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/straight.maz.txt");
        Runner runner = new Runner(maze);
        int initialY = runner.getY();
        runner.moveForward();
        assertEquals(initialY + 1, runner.getY()); 
    }

    @Test
    // Test that moveForward() correctly updates the runner's position (westbound)
    void testForwardMovementWest() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/straight.maz.txt");
        Runner runner = new Runner(maze);
        int initialY = runner.getY();
        runner.setDirection('W');
        runner.moveForward();
        assertEquals(initialY - 1, runner.getY()); 
    }

    @Test
    // Test that moveForward() correctly updates the runner's position (northbound)
    void testForwardMovementNorth() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/straight.maz.txt");
        Runner runner = new Runner(maze);
        int initialX = runner.getX();
        runner.setDirection('N');
        runner.moveForward();
        assertEquals(initialX - 1, runner.getX()); 
    }

    @Test
    // Test that moveForward() correctly updates the runner's position (southbound)
    void testForwardMovementSouth() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/straight.maz.txt");
        Runner runner = new Runner(maze);
        int initialX = runner.getX();
        runner.setDirection('S');
        runner.moveForward();
        assertEquals(initialX + 1, runner.getX()); 
    }

    @Test
    // Test that turnRight() changes direction correctly
    void testTurnRight() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/small.maz.txt");
        Runner runner = new Runner(maze);
        runner.setDirection('N'); 
        runner.turnRight();
        assertEquals('E', runner.getDirection());
    }

    @Test
    // Test that turnLeft() changes direction correctly
    void testTurnLeft() {
        Maze maze = new Maze();
        maze.intializeMaze("examples/small.maz.txt");
        Runner runner = new Runner(maze);
        runner.setDirection('N'); 
        runner.turnLeft();
        assertEquals('W', runner.getDirection());
    }

}
