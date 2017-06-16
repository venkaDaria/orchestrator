package com.globallogic.orchestrator.connector.exception;

import com.globallogic.orchestrator.base.exception.ApplicationException;

/**
 * Exception's codes interval is 100100-100199
 */
public class FileProcessException extends ApplicationException {
    private static final int CODE = 100100;
    private static final String MESSAGE = "Problems with file";

    public FileProcessException() {
        super(CODE, MESSAGE);
    }

    public FileProcessException(final String message) {
        super(CODE, message);
    }

    public FileProcessException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public FileProcessException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }

    public FileProcessException(final int code, final String message) {
        super(code, message);
    }

    public FileProcessException(final int code, final String message, final Throwable cause) {
        super(code, message, cause);
    }
}
