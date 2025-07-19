package com.homeopathy.homeopathyclinic.config;


import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties
public class TwilioConfig {
    @Value("${twilio.accountSid}")
    private String accountSid;
    @Value("${twilio.authToken}")
    private String authToken;
    private String phoneNumber;
    @PostConstruct
    public void init(){
        Twilio.init(accountSid,authToken);
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
