package com.globallogic.orchestrator.dao.exception;

import com.globallogic.orchestrator.base.exception.ApplicationException;

/**
 * Exception's codes interval is 100400-100499
 */
public class SystemNotSupportException extends ApplicationException {
    private static final int CODE = 100400;
    private static final String MESSAGE = "System isn't support";

    public SystemNotSupportException() {
        super(CODE, MESSAGE);
    }

    public SystemNotSupportException(final String message) {
        super(CODE, message);
    }

    public SystemNotSupportException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public SystemNotSupportException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }

    public SystemNotSupportException(final int code, final String message) {
        super(code, message);
    }

    public SystemNotSupportException(final int code, final String message, final Throwable cause) {
        super(code, message, cause);
    }
}

