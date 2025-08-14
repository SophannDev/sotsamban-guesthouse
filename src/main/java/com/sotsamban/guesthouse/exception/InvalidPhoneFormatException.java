package com.sotsamban.guesthouse.exception;

public class InvalidPhoneFormatException extends RuntimeException {

    public InvalidPhoneFormatException(String message) {
        super(message);
    }

    public InvalidPhoneFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
