package com.globallogic.orchestrator.base;

public class FileNotFoundException extends FileProcessException {
    private static final int CODE = 100101;
    private static final String MESSAGE = "File wasn't found";

    public FileNotFoundException() {
        super();
    }

    public FileNotFoundException(final String message) {
        super(message);
    }

    public FileNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FileNotFoundException(final Throwable cause) {
        super(cause);
    }
}
