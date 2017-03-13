package com.pw.exception;

/**
 * Created by Zach on 3/11/17.
 */
public class UsernameLengthTooShort extends RuntimeException {
    public UsernameLengthTooShort(String message) {
        super(message);
    }
}
