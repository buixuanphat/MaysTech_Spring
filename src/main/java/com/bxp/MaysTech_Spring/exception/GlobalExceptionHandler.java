package com.bxp.MaysTech_Spring.exception;

import com.bxp.MaysTech_Spring.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception ex) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(ResponseCode.UNCATEGORIZED.getCode());
        apiResponse.setMessage(ResponseCode.UNCATEGORIZED.getMessage());
        return ResponseEntity.internalServerError().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException ex) {
        ResponseCode responseCode = ex.getAppError();

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setStatusCode(responseCode.getCode());
        apiResponse.setMessage(responseCode.getMessage());

        return ResponseEntity.status(responseCode.getCode()).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {

        String message = ex.getFieldError().getDefaultMessage();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(ResponseCode.BAD_REQUEST.getCode());
        apiResponse.setMessage(message);

        return ResponseEntity.status(ResponseCode.BAD_REQUEST.getCode()).body(apiResponse);
    }
}
