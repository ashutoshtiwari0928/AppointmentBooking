package com.homeopathy.homeopathyclinic.service.impl;

import com.homeopathy.homeopathyclinic.model.Doctor.Doctor;
import com.homeopathy.homeopathyclinic.repository.DoctorRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class DoctorService {
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public DoctorRepository doctorRepository;
    @Autowired
    public JWTServiceImpl jwtService;
    @Autowired
    public AuthenticationManager authenticationManager;
    public Doctor register(@NotNull Doctor doctor)
            throws SQLIntegrityConstraintViolationException {
        System.out.println(doctor);
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        if(doctor.getRole()==null)doctor.setRole("USER");
        return doctorRepository.save(doctor);
    }
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
