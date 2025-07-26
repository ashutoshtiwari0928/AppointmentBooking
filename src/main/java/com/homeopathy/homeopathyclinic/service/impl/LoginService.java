package com.homeopathy.homeopathyclinic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTServiceImpl jwtService;

    public String verifyEmail(String email, String password) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken
                        (email,password));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(email,password);
        }
        else{
            throw new UsernameNotFoundException("User not validated.");
        }
    }
}
