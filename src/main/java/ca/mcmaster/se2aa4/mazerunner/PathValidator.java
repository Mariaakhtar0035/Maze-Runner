/**
 * PathValidator class is responsible for validating a given path sequence in the maze.
 * It uses a Runner object to simulate the path and checks if the path leads from the entry point
 * to the exit point of the maze.
 */
package ca.mcmaster.se2aa4.mazerunner;


/**
 * Validates the provided path sequence.
 * The path sequence can include moves ('F' for forward, 'R' for right, 'L' for left)
 * and digits to indicate repeated moves (e.g. "2F" means move forward twice).
 *
 * @param pathSequence The path sequence to validate.
 */
public class PathValidator {
    private final Runner runner;

    protected final Command moveForward;
    protected final Command turnRight;
    protected final Command turnLeft;

    public PathValidator(Runner runner) {
        this.runner = runner;

        this.moveForward = new MoveForward(runner);
        this.turnRight = new TurnRight(runner);
        this.turnLeft = new TurnLeft(runner);
    }

    public String validatePath(String pathSequence) {
        int moveCount = 1;
    
        // Iterate through each character in the path sequence
        for (char move : pathSequence.toCharArray()) {

            // If the character is a digit, update the move count for repeated moves
            if (Character.isDigit(move)) {
                moveCount = move - '0'; // Convert char to integer
                continue;
            }
    
            for (int i = 0; i < moveCount; i++) {
                if (move == 'F') {
                    moveForward.execute();
                } else if (move == 'R') {
                    turnRight.execute();
                } else if (move == 'L') {
                    turnLeft.execute();
                }
            }
            moveCount = 1;
        }
    
        // Check if the runner has reached the exit 
        System.out.println((runner.getX() == runner.getEndX() && runner.getY() == runner.getEndY()) ? "Correct path" : "Incorrect path");

        if (runner.getX() == runner.getEndX() && runner.getY() == runner.getEndY()) {
            return "Correct path";
        }
        else {
            return "Incorrect path";
        }
    }
}
