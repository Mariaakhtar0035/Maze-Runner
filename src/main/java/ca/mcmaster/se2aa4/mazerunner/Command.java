package ca.mcmaster.se2aa4.mazerunner;

public interface  Command {
    boolean canExecute();
    boolean execute();
}
