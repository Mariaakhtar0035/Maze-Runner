/**
 * Path class represents a sequence of instructions for navigating through a maze.
 * It supports adding instructions, and outputting the path in both canonical and factorized formats.
 */
package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class Path {
    private final ArrayList<Character> sequence;
    private String pathCanonical = "";
    private String pathFactorized = "";

    public Path() {
        sequence = new ArrayList<>();
    }

    public void addInstruction(char instruction) {
        sequence.add(instruction);
    }

    /**
     * Outputs the path in canonical format.
     * The canonical format is a space-separated sequence of instructions.
     * For example, "F F R F".
     */
    public void outputPathCanonical() {
        char currentInstruction = sequence.get(0);

        for (char instruction : sequence) {

            // Add a space when the instruction changes
            if (instruction != currentInstruction) {
                pathCanonical += " ";
            }
            pathCanonical += instruction;
            currentInstruction = instruction;
        }
        
        System.out.println(pathCanonical);

    }

     /**
     * Outputs the path in factorized format.
     * The factorized format compresses repeated instructions with counts.
     * For example, "2F R F".
     */
    public void outputPathFactorized() {
        char currentInstruction = sequence.get(0);
        int count = 0;

        for (char instruction : sequence) {
            if (instruction == currentInstruction) {
                count++;
            }
            else {
                // Add the count and instruction to the factorized path (skip count if it's 1)
                if (count != 1 ) pathFactorized += count;
                pathFactorized = pathFactorized + currentInstruction + " ";
                currentInstruction = instruction;
                count = 1;
            }
        }

        // Add the last instruction and its count to the factorized path
        if (count != 1 ) pathFactorized += count;
        pathFactorized = pathFactorized + currentInstruction + " ";
        
        System.out.println(pathFactorized);
        
    } 
    
}
