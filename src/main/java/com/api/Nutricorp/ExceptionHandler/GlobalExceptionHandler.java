package com.api.Nutricorp.ExceptionHandler;

import com.api.Nutricorp.DTO.ApiResponse;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandler extends RuntimeException {
    public GlobalExceptionHandler(String message) {
        super(message);
    }

    public ResponseEntity<ApiResponse> handleInvalidOTP(InvalidOTPExceptionHandler e){
        return ResponseEntity.badRequest().body(new ApiResponse(false,e.getMessage()));
    }

    public ResponseEntity<ApiResponse> handleExpiredOTP(OTPExpiredExceptionHandler e){
        return ResponseEntity.badRequest().body(new ApiResponse(false,e.getMessage()));
    }
}
