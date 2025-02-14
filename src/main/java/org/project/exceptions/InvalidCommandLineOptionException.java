package org.project.exceptions;

public class InvalidCommandLineOptionException extends RuntimeException{
    public InvalidCommandLineOptionException(String message) {
        super(message);
    }
}
