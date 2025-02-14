package org.project.exceptions;

public class NoInputFileException extends InvalidCommandLineOptionException{
    public NoInputFileException() {
        super("No input file path");
    }
}
