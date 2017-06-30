package com.globallogic.orchestrator.connector.exception;

import com.globallogic.orchestrator.base.exception.ApplicationException;

/**
 * Exception's codes interval is 100500-100599
 */
public class MongodbOperationException extends ApplicationException {
    private static final int CODE = 100500;
    private static final String MESSAGE = "Problems with database mongodb";

    public MongodbOperationException() {
        super(CODE, MESSAGE);
    }

    public MongodbOperationException(final String message) {
        super(CODE, message);
    }

    public MongodbOperationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public MongodbOperationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }

    public MongodbOperationException(final int code, final String message) {
        super(code, message);
    }

    public MongodbOperationException(final int code, final String message, final Throwable cause) {
        super(code, message, cause);
    }
}
