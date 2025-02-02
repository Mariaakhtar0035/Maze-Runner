package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner **");

        CommandLine cmd = parseCommandLine(args);
        if (cmd == null) {
            logger.error("Failed to parse command-line arguments.");
            return;
        }

        if (!cmd.hasOption("i")) {
            logger.error("Missing required option: -i (Path to the maze file)");
            return;
        }

        String mazeFile = cmd.getOptionValue("i");
        Maze maze = initializeMaze(mazeFile);
        if (maze == null) return;

        Runner runner = new Runner(maze);
        Path path = new Path();

        if (cmd.hasOption("p")) {
            validatePath(cmd.getOptionValue("p"), runner);
        } else {
            computePath(maze, path, runner);
        }

        logger.info("** End of MazeRunner **");
    }
    

    private static CommandLine parseCommandLine(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "Path to the maze file");
        options.addOption("p", true, "Maze path sequence");

        CommandLineParser parser = new DefaultParser();
        try {
            return parser.parse(options, args);
        } catch (ParseException e) {
            logger.error("Error parsing command-line arguments: {}", e.getMessage());
            return null;
        }
    }

    private static Maze initializeMaze(String mazeFile) {
        try {
            logger.info("Initializing maze from file: {}", mazeFile);
            Maze maze = new Maze();
            maze.intializeMaze(mazeFile);
            return maze;
        } catch (Exception e) {
            logger.error("Error initializing maze: {}", e.getMessage());
            return null;
        }
    }

    private static void validatePath(String pathSequence, Runner runner) {
        try {
            logger.info("Validating provided path sequence: {}", pathSequence);
            PathValidator pathValidator = new PathValidator(runner);
            pathValidator.validatePath(pathSequence);
        } catch (Exception e) {
            logger.error("Error validating path: {}", e.getMessage());
        }
    }

    private static void computePath(Maze maze, Path path, Runner runner) {
        try {
            logger.info("Computing path...");
            RightHandSolver rightHandSolver = new RightHandSolver(maze, path, runner);
            rightHandSolver.computePath();
            path.outputPathCanonical();
        } catch (Exception e) {
            logger.error("Error computing path: {}", e.getMessage());
        }
    }
}