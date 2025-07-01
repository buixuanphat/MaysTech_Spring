package com.bxp.MaysTech_Spring.exception;

public class AppException extends RuntimeException {
    private ResponseCode responseCode;

    public AppException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public ResponseCode getAppError() {
        return responseCode;
    }
}
