package com.globallogic.orchestrator.connector.exception;

import com.globallogic.orchestrator.base.exception.ApplicationException;

/**
 * Exception's codes interval is 100300-100399
 */
public class DbException extends ApplicationException {
    private static final int CODE = 100100;
    private static final String MESSAGE = "Problems with db";

    public DbException() {
        super(CODE, MESSAGE);
    }

    public DbException(final String message) {
        super(CODE, message);
    }

    public DbException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public DbException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }

    public DbException(final int code, final String message) {
        super(code, message);
    }

    public DbException(final int code, final String message, final Throwable cause) {
        super(code, message, cause);
    }
}
