package com.api.Nutricorp.ExceptionHandler;

public class OTPExpiredExceptionHandler extends RuntimeException {
    public OTPExpiredExceptionHandler(String message) {
        super(message);
    }
}
