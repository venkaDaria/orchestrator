package com.globallogic.orchestrator.dao.base.exception;

public class ApplicationException extends RuntimeException  {
    private final int code;

    public ApplicationException(final int code, final String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(final int code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
