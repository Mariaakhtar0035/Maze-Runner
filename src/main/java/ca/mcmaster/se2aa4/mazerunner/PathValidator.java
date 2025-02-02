package ca.mcmaster.se2aa4.mazerunner;


public class PathValidator {
    private final Runner runner;

    public PathValidator(Runner runner) {
        this.runner = runner;
    }

    public void validatePath(String pathSequence) {
        int moveCount = 1;
    
        for (char move : pathSequence.toCharArray()) {
            if (Character.isDigit(move)) {
                moveCount = move - '0';
                continue;
            }
    
            for (int i = 0; i < moveCount; i++) {
                if (move == 'F') {
                    runner.moveForward();
                } else if (move == 'R') {
                    runner.turnRight();
                } else if (move == 'L') {
                    runner.turnLeft();
                }
            }
            moveCount = 1;
        }
    
        System.out.println((runner.getX() == runner.getEndX() && runner.getY() == runner.getEndY()) ? "Correct path" : "Incorrect path");
    }
}
