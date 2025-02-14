package org.project.exceptions;

public class InvalidOutputTypeException extends InvalidCommandLineOptionException {
    public InvalidOutputTypeException() {
        super("Invalid output type");
    }
}
