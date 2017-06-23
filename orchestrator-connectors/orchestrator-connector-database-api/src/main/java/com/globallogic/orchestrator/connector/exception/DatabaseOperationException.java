package com.globallogic.orchestrator.connector.exception;

import com.globallogic.orchestrator.base.exception.ApplicationException;

/**
 * Exception's codes interval is 100300-100399
 */
public class DatabaseOperationException extends ApplicationException {
    private static final int CODE = 100300;
    private static final String MESSAGE = "Problems with database";

    public DatabaseOperationException() {
        super(CODE, MESSAGE);
    }

    public DatabaseOperationException(final String message) {
        super(CODE, message);
    }

    public DatabaseOperationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public DatabaseOperationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }

    public DatabaseOperationException(final int code, final String message) {
        super(code, message);
    }

    public DatabaseOperationException(final int code, final String message, final Throwable cause) {
        super(code, message, cause);
    }
}
