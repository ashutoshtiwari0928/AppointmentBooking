package com.homeopathy.homeopathyclinic.controller;

import com.homeopathy.homeopathyclinic.service.impl.TwilioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
// I have created these rest controllers for future use. I will need a suscription
// for working with twilio.
@RestController
@RequestMapping("/api/otp")
public class OtpController {
    private final TwilioService twilioService;

    public OtpController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> payload) {
        twilioService.sendOtp(payload.get("phone"));
        return ResponseEntity.ok("OTP Sent");
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> payload) {
        boolean valid = twilioService.verifyOtp(payload.get("phone"), payload.get("otp"));
        return valid ? ResponseEntity.ok("Verified") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
    }
}
