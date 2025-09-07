package com.api.Nutricorp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OTPRequest {
    private String phoneNumber;
    private String code;
}
