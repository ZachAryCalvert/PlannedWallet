package com.pw.exception;

/**
 * Created by Zach on 3/11/17.
 */
public class PasswordLengthTooShort extends RuntimeException {
    public PasswordLengthTooShort(String message) {
        super(message);
    }
}
