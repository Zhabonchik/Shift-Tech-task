package org.project;

import org.apache.commons.cli.ParseException;
import org.project.entities.Employee;
import org.project.entities.Manager;
import org.project.exceptions.InvalidCommandLineOptionException;
import org.project.tools.DataProcessor;
import org.project.tools.MyCommandLineOptions;
import org.project.tools.MyCommandLineParser;
import org.project.tools.MyFileParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        runApp(args);
    }

    public static void runApp(String[] args) {
        try {

            logger.debug("Parsing command line arguments...");
            MyCommandLineOptions options = MyCommandLineParser.parse(args);
            logger.info("Command line arguments parsed: {}", options);

            MyFileParser fileParser = new MyFileParser();
            logger.info("Parsing data from input file: {}", options.getInputFile());
            fileParser.parsePeopleFromFile(options.getInputFile());

            List <Manager> managers = fileParser.getManagers();
            List <Employee> employees = fileParser.getEmployees();
            List <String> incorrectData = fileParser.getIncorrectData();
            logger.info("Input file parsing finished. Found {} managers, {} employees, {} invalid data.",
                    managers.size(), employees.size(), incorrectData.size());

            logger.debug("Data processing...");
            DataProcessor.processData(managers, employees, incorrectData, options);
            logger.info("Finished processing data.");

        } catch (ParseException e) {
            logger.error("Exception while parsing command line: {}", e.getMessage());
        } catch (InvalidCommandLineOptionException e) {
            logger.error("Invalid command line options: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("unexpected exception: ", e);
        }
    }
}
