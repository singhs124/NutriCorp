package com.api.Nutricorp.Service;

import com.api.Nutricorp.ExceptionHandler.OTPExpiredExceptionHandler;
import com.api.Nutricorp.dto.OTPData;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class otpService {
    private final int len = 6;
    private Map<String, OTPData> storage = new HashMap<>();

    public String generateOtp(){
        StringBuilder otp = new StringBuilder(len);
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0 ; i < len; i++){
            otp.append(secureRandom.nextInt(10));
        }
        return otp.toString();
    }

    public void storeOtp(String phoneNumber, OTPData otp){
        storage.put(phoneNumber,otp);
//        log.info("Added otp for phoneNumber: "+ phoneNumber);
    }

    public boolean verifyOtp(String phoneNumber, OTPData otp){
        boolean phoneNumberExists = storage.containsKey(phoneNumber);
        if(!phoneNumberExists){
//            log.warn("Phone Number not Found | OTP not Generated!");
            return false;
        }
        OTPData storedOtp = storage.get(phoneNumber);
        if(System.currentTimeMillis()> storedOtp.getExpiryTime()){
            storage.remove(phoneNumber);
            System.out.println("OTP Expired! Get new OTP");
            throw new OTPExpiredExceptionHandler("OTP has Expired!");
        }
        if(!storedOtp.getOtp().equals(otp.getOtp())){
            System.out.println("Incorrect OTP");
//            log.warn("Invalid OTP attempt for phone : {}" , phoneNumber);
            return false;
        }
        System.out.println("User is Authenticated");
//        log.info("Otp is confirmed for phone: "+ phoneNumber);
        return true;
    }
}
