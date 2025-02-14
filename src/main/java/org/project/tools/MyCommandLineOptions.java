package org.project.tools;

import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Main;
import org.project.exceptions.InvalidCommandLineOptionException;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyCommandLineOptions {

    private String inputFile;
    private String outputType;
    private String outputFile;
    private String sortingType;
    private String sortingOrder;

    MyCommandLineOptions(String inputFile, String outputType, String outputFile, String sortingType, String sortingOrder)
            throws InvalidCommandLineOptionException {

        if (inputFile == null || inputFile.isEmpty()) {
            throw new InvalidCommandLineOptionException("No input file specified");
        } else {
            this.inputFile = inputFile;
        }

        if (!outputType.equals("console") && !outputType.equals("file")) {
            throw new InvalidCommandLineOptionException("Invalid output type: " + outputType);
        } else {
            this.outputType = outputType;
        }

        if (outputType.equals("file") && outputFile == null) {
            throw new InvalidCommandLineOptionException("Output type file not specified");
        } else if (outputType.equals("console") && outputFile != null) {
            throw new InvalidCommandLineOptionException("Output type console, no output file needed");
        } else {
            this.outputFile = outputFile;
        }

        if (!sortingType.equals("default") && !sortingType.equals("name") && !sortingType.equals("salary")) {
            throw new InvalidCommandLineOptionException("Invalid sorting type: " + sortingType);
        } else {
            this.sortingType = sortingType;
        }

        if (!sortingOrder.equals("default") && !sortingOrder.equals("asc") && !sortingOrder.equals("desc")) {
            throw new InvalidCommandLineOptionException("Invalid sorting order: " + sortingOrder);
        } else if (sortingType.equals("default") && !sortingOrder.equals("default")) {
            throw new InvalidCommandLineOptionException("Sorting order must not be specified");
        } else if (!sortingType.equals("default") && sortingOrder.equals("default")){
            throw new InvalidCommandLineOptionException("Sorting order must be specified");
        } else {
            this.sortingOrder = sortingOrder;
        }
    }
}
