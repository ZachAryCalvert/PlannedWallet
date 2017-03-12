package com.pw.exception;

/**
 * Created by Zach on 3/11/17.
 */
public class PasswordLengthTooShort extends RuntimeException {

    public PasswordLengthTooShort() {
    }

    public PasswordLengthTooShort(String message) {
        super(message);
    }

    public PasswordLengthTooShort(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordLengthTooShort(Throwable cause) {
        super(cause);
    }

    public PasswordLengthTooShort(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
