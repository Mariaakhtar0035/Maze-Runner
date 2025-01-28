package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", true, "Path to the maze file");
        options.addOption("p", true, "Maze path sequence");
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String mazeFile = cmd.getOptionValue("i");
                logger.info("creating maze");
                Maze maze = new Maze();
                Path path = new Path();
                maze.loadMaze(mazeFile);
                maze.findEntry();
                maze.findExit();
                Runner runner = new Runner(maze);

                if (cmd.hasOption("p")) {
                    String pathSequence = cmd.getOptionValue("p");
                    runner.validatePath(pathSequence);
                }
                else {
                    logger.info("**** Computing path");
                    logger.info("PATH NOT COMPUTED");
                }
                
            }
            
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        
        logger.info("** End of MazeRunner");
    }
}