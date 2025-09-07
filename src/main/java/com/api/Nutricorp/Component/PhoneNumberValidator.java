package com.api.Nutricorp.Component;

import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidator {

    public boolean validate(String phoneNumber){
        if(phoneNumber == null || phoneNumber.trim().isEmpty()){
            return false;
        }
        String regex = "^(\\+91|91|0)?[6-9]\\d{9}$"; //Indian Mobile Numbers with +91 country code
        return phoneNumber.matches(regex);
    }
}
