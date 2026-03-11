package com.sthi.re.exception;

/**
 * Base exception for re vertical.
 */
public class ReException extends RuntimeException {

    public ReException(String message) {
        super(message);
    }

    public ReException(String message, Throwable cause) {
        super(message, cause);
    }
}
