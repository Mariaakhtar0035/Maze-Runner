package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class Path {
    private final ArrayList<Character> sequence;
    private String pathCanonical = "";

    public Path() {
        sequence = new ArrayList<>();
    }

    public void addInstruction(char instruction) {
        sequence.add(instruction);
    }

    public void outputPathCanonical() {
        
        for (char instruction : sequence) {
            pathCanonical += instruction;
        }
        
        System.out.println(pathCanonical);

    }
    
}
