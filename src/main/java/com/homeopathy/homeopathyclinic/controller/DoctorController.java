package com.homeopathy.homeopathyclinic.controller;

import com.homeopathy.homeopathyclinic.model.Doctor.Doctor;
import com.homeopathy.homeopathyclinic.model.DoctorLoginDetails;
import com.homeopathy.homeopathyclinic.service.impl.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Doctor doctor){
        try{
            return new ResponseEntity<>(doctorService.register(doctor), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
}
