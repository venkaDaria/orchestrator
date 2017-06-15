package com.globallogic.orchestrator.connector;

public class FileProcessException extends RuntimeException {
    public FileProcessException() {
        super();
    }

    public FileProcessException(final String message) {
        super(message);
    }

    public FileProcessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FileProcessException(final Throwable cause) {
        super(cause);
    }
}
