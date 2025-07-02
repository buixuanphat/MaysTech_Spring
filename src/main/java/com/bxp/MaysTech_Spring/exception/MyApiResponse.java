package com.bxp.MaysTech_Spring.exception;

public enum MyApiResponse {

    //2XX
    OK(200, "OK"),
    CREATED(201, "Created"),
    NO_CONTENT(204, "No Content"),

    // 4XX
    BLANK_EMAIL(400, "Email is required"),
    BLANK_PASSWORD(400, "Password is required"),
    WRONG_EMAIL_FORMAT(400, "Email invalid"),
    WRONG_PASSWORD_FORMAT(400, "Password must be between 8 and 12 characters"),
    USER_ALREADY_EXISTS(409, "User already exists"),
    CATEGORY_ALREADY_EXISTS(409, "Category already exists"),
    NOT_FOUND(404, "Not found"),
    INVALID_CREDENTIALS(401, "Invalid email or password"),
    FORBIDDEN(403, "Access denied"),
    BAD_REQUEST(400, "Bad request"),

    //5XX
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    DATABASE_ERROR(500, "Database error"),
    ;
    private int code;
    private String message;

    MyApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
