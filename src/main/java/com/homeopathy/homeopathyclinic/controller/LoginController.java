package com.homeopathy.homeopathyclinic.controller;

import com.homeopathy.homeopathyclinic.model.PatientLoginDetails;
import com.homeopathy.homeopathyclinic.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/verifyEmail")
    public ResponseEntity<?> verify(@RequestBody PatientLoginDetails user) {
        try{
            String token = loginService.
                    verifyEmail(user.getUsername(), user.getPassword());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        catch(UsernameNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
