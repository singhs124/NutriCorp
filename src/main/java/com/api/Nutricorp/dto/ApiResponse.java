package com.api.Nutricorp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String error;

    public ApiResponse(boolean success, T data){
        this.success = success;
        this.data = data;
    }

    public ApiResponse(boolean success, String error){
        this.success = success;
        this.error = error;
    }
}
