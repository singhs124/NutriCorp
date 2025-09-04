package com.api.Nutricorp.Controller;

import com.api.Nutricorp.Service.MobileOtpAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class MobileOtpAuthController {
    @Autowired
    MobileOtpAuthService mobileOtpAuthService;

    @GetMapping("/mobileNumber")
    public void getOtp(@RequestParam String number){
        mobileOtpAuthService.sendOtp(number);
    }

    @GetMapping("/login")
    public void authenticateOtp(@RequestParam String number, @RequestParam String otp){
        mobileOtpAuthService.authenticateUser(number,otp);
    }
}
