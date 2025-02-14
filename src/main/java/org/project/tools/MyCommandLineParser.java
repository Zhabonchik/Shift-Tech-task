package org.project.tools;

import org.apache.commons.cli.*;
import org.project.exceptions.InvalidCommandLineOptionException;

public class MyCommandLineParser {
    public static MyCommandLineOptions parse(String[] args) throws ParseException, InvalidCommandLineOptionException {

        Options options = getCommandLineOptions();

        CommandLineParser parser = new DefaultParser();

        CommandLine commandLine = parser.parse(options, args);

        String inputFile = commandLine.hasOption("i") ? commandLine.getOptionValue("i")
                : null;
        String outputType = commandLine.hasOption("o") ? commandLine.getOptionValue("o")
                : "console";
        String outputFile = commandLine.hasOption("path") ? commandLine.getOptionValue("path")
                : null;
        String sortingType = commandLine.hasOption("s") ? commandLine.getOptionValue("s")
                : "default";
        String sortingOrder = commandLine.hasOption("order") ? commandLine.getOptionValue("order")
                : "default";

        return new MyCommandLineOptions(inputFile, outputType, outputFile, sortingType, sortingOrder);
    }

    private static Options getCommandLineOptions() {
        Options options = new Options();

        Option inputFile = Option.builder("i")
                .longOpt("input").required(true).desc("Input file directory").hasArg().build();
        Option outputType = Option.builder("o")
                .longOpt("output").required(false).desc("Output type").hasArg().build();
        Option outputFile = Option.builder()
                .longOpt("path").required(false).desc("Output file directory").hasArg().build();
        Option sortingType = Option.builder("s")
                .longOpt("sort").required(false).desc("Sorting type").hasArg().build();
        Option sortingOrder = Option.builder()
                .longOpt("order").required(false).desc("Sorting order").hasArg().build();

        options.addOption(inputFile)
                .addOption(outputType)
                .addOption(outputFile)
                .addOption(sortingType)
                .addOption(sortingOrder);

        return options;
    }
}
