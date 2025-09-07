package com.api.Nutricorp.Service;

import com.api.Nutricorp.config.SMSProviderConfig;
import com.api.Nutricorp.dto.OTPData;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
    private final SMSProviderConfig smsProviderConfig;

    public SMSService(SMSProviderConfig smsProviderConfig){
        this.smsProviderConfig = smsProviderConfig;
    }


    public void sendOTP(String number, String otp){
        String message = smsProviderConfig.getMessage().replace("{otp}" , otp);

        switch (smsProviderConfig.getName().toLowerCase()){
            case "mock":
                System.out.println("Mock SMS sent to "+ number + ": "+message);
                break;
            default:
//                log.error(smsProviderConfig.getName() + "SMS Provider is not Supported!")
                throw new UnsupportedOperationException("SMS Provider not Supported!");
        }

    }
}
