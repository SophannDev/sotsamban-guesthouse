package com.sotsamban.guesthouse.common;

public enum StatusCode {

    SUCCESS(200, "Success", 200),
    NOT_FOUND(404, "Not Found", 404),

    // exist
    EMAIL_ALREADY_EXISTS(409, "Email already exists", 409),
    ROOM_TYPE_ALREADY_EXISTS(409, "Room type already exists", 409);
    private final String message;
    private final int code;
    private final int httpCode;

    StatusCode(final int code, final String message, int httpCode) {

        this.message = message;
        this.code = code;
        this.httpCode = httpCode;
    }

    public String getMessage() {

        return this.message;

    }

    public int getCode() {

        return code;

    }

    public int getHttpCode() {

        return httpCode;

    }

}
