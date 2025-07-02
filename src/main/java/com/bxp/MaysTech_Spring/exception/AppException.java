package com.bxp.MaysTech_Spring.exception;

public class AppException extends RuntimeException {
    private MyApiResponse myApiResponse;

    public AppException(MyApiResponse myApiResponse) {
        super(myApiResponse.getMessage());
        this.myApiResponse = myApiResponse;
    }

    public MyApiResponse getAppError() {
        return myApiResponse;
    }
}
