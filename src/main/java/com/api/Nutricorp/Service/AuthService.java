package com.api.Nutricorp.Service;

import com.api.Nutricorp.Component.PhoneNumberValidator;
import com.api.Nutricorp.DTO.OTPData;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final OTPService otpService;
    private final PhoneNumberValidator phoneNumberValidator;
    private final SMSService smsService;

    public AuthService(OTPService otpService, PhoneNumberValidator phoneNumberValidator, SMSService smsService){
        this.otpService = otpService;
        this.phoneNumberValidator = phoneNumberValidator;
        this.smsService = smsService;
    }

    public void initOTPProcess(String phoneNumber){
        if(!phoneNumberValidator.validate(phoneNumber)){
//            log.warn("Provided Phone Number is not correct");
            throw new IllegalArgumentException("Phone Number is Wrong!");
        }
        OTPData otp = otpService.generateOtp();
        //Is it good to store otp here or in generateOtp() method
        otpService.storeOtp(phoneNumber,otp);
        smsService.sendOTP(phoneNumber,otp.getOtp());
    }

    public void loginWithOtp(String number, String code){
        otpService.verifyOtp(number,code);
    }


}
