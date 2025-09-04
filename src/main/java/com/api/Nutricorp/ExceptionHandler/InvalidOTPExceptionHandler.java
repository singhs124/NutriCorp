package com.api.Nutricorp.ExceptionHandler;

public class InvalidOTPExceptionHandler extends RuntimeException {
    public InvalidOTPExceptionHandler(String message) {
        super(message);
    }
}
