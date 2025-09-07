package com.api.Nutricorp.Controller;

import com.api.Nutricorp.Service.AuthService;
import com.api.Nutricorp.Service.MobileOtpAuthService;
import com.api.Nutricorp.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class MobileOtpAuthController {
    private final AuthService authService;

    public MobileOtpAuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<String>> getOtp(@RequestBody String number){
//        log.info("OTP request received for phone: "+ number);
        try{
            authService.initOTPProcess(number);
            return ResponseEntity.ok(new ApiResponse<>(true,"OTP Sent Successfully"));
        } catch (Exception e){
//            log.error("Failed to Send OTP to {}: {} ", number, e.getMessage);
            System.out.println("Failed to Send OTP: " + e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse<>(false,"OTP not sent"));
        }
    }

//    @GetMapping("/login")
//    public void authenticateOtp(@RequestParam String number, @RequestParam String otp){
//        mobileOtpAuthService.authenticateUser(number,otp);
//    }
}
