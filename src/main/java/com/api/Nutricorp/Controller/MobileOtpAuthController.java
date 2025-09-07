package com.api.Nutricorp.Controller;

import com.api.Nutricorp.DTO.OTPRequest;
import com.api.Nutricorp.Service.AuthService;
import com.api.Nutricorp.DTO.ApiResponse;
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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> authenticateOtp(@RequestBody OTPRequest request){
        try{
            String number = request.getPhoneNumber();
            String otp = request.getCode();
            authService.loginWithOtp(number,otp);
            return ResponseEntity.ok(new ApiResponse<>(true,"Successfullly Authenticated!"));
        } catch (Exception e){
            System.out.println("Failed to Authenticate User: "+e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, "User is not Authenticated"));
        }
    }
}
