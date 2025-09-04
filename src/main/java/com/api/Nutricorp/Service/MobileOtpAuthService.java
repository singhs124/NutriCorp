package com.api.Nutricorp.Service;

import com.api.Nutricorp.dto.OTPData;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MobileOtpAuthService {
    private Map<String , OTPData> storage = new HashMap<>();

    public boolean validateMobileNumber(String phoneNumber){
        if(phoneNumber == null || phoneNumber.trim().isEmpty()){
            return false;
        }
        String regex = "^(\\+91|91|0)?[6-9]\\d{9}$"; //Indian Mobile Numbers with +91 country code
        return phoneNumber.matches(regex);
    }

    public OTPData generateOtp(int length){
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder(length);
        for(int i = 0;  i < length; i++){
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        long expiryTime = System.currentTimeMillis() + (5*60*1000);
        return new OTPData(otp.toString(),expiryTime);
    }

    public void storeOtp(String phoneNumber, OTPData otp){
        storage.put(phoneNumber,otp);
    }

    public void sendOtp(String phoneNumber){
        phoneNumber = phoneNumber.replaceAll("[-\\s]","");
        if(!validateMobileNumber(phoneNumber)){
            System.out.println("Not correct Phone Number");
            return;
        }
        OTPData otp = generateOtp(6);
        storeOtp(phoneNumber,otp);
        for(Map.Entry<String , OTPData> entry: storage.entrySet()){
            System.out.println("Phone: " + entry.getKey() +
                    ", OTP: " + entry.getValue().getOtp() +
                    ", Expiry: " + entry.getValue().getExpiryTime());
        }
//        To-DO 3rd party otp sender
        System.out.println(otp.getOtp());
    }

    public void authenticateUser(String phoneNumber, String otp){
        boolean phoneNumberExists = storage.containsKey(phoneNumber);
        if(!phoneNumberExists){
            System.out.println("Phone Number not Found | OTP not Generated!");
            return;
        }
        OTPData storedOtp = storage.get(phoneNumber);
        if(System.currentTimeMillis()> storedOtp.getExpiryTime()){
            storage.remove(phoneNumber);
            System.out.println("OTP Expired! Get new OTP");
            return;
        }
        if(!storedOtp.getOtp().equals(otp)){
            System.out.println("Incorrect OTP");
            return;
        }
        System.out.println("User is Authenticated");
        return;
    }

}
