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

    public void outputPathCanonical() {
        char currentInstruction = sequence.get(0);

        for (char instruction : sequence) {
            if (instruction != currentInstruction) {
                pathCanonical += " ";
            }
            pathCanonical += instruction;
            currentInstruction = instruction;
        }
        
        System.out.println(pathCanonical);

    }

    public void outputPathFactorized() {
        char currentInstruction = sequence.get(0);
        int count = 0;

        for (char instruction : sequence) {
            if (instruction == currentInstruction) {
                count++;
            }
            else {
                if (count != 1 ) pathFactorized += count;
                pathFactorized = pathFactorized + currentInstruction + " ";
                currentInstruction = instruction;
                count = 1;
            }
        }

        if (count != 1 ) pathFactorized += count;
        pathFactorized = pathFactorized + currentInstruction + " ";
        
        System.out.println(pathFactorized);
        
    } 
    
}
