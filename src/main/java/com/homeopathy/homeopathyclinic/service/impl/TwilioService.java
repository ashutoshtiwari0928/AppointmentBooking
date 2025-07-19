package com.homeopathy.homeopathyclinic.service.impl;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
public class TwilioService {
    @Value("${twilio.phoneNumber}")
    private String fromNumber;
    private final Map<String,String> otpMap = new HashMap<>();

    public void sendOtp(String phone){
        String otp = String.valueOf(new Random().nextInt(900000)+100000);
        Message.creator(
                new PhoneNumber(phone),
                new PhoneNumber(fromNumber),
                "Your otp is: "+otp
        ).create();
        otpMap.put(phone,otp);
    }
    public boolean verifyOtp(String phone,String inputOtp){
        String actualOtp = otpMap.get(phone);
        if(actualOtp!=null){
            if(actualOtp.equals(inputOtp)){
                otpMap.remove(phone);
                return true;
            }
        }
        return false;
    }
}
