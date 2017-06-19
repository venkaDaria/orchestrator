package com.globallogic.orchestrator.dao.connector.exception;

public class FileNotFoundException extends FileProcessException {
    private static final int CODE = 100101;
    private static final String MESSAGE = "File wasn't found";

    public FileNotFoundException() {
        super(CODE, MESSAGE);
    }

    public FileNotFoundException(final String message) {
        super(CODE, message);
    }

    public FileNotFoundException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public FileNotFoundException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}
