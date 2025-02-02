/**
 * MazeSolver interface defines the contract for maze-solving algorithms.
 * Any class implementing this interface must provide an implementation for the `computePath` method,
 * which computes a path through the maze from the entry point to the exit.
 */
package ca.mcmaster.se2aa4.mazerunner;

public interface MazeSolver {

    void computePath();
}
