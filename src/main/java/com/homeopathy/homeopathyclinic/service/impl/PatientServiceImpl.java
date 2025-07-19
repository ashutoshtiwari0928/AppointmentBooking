package com.homeopathy.homeopathyclinic.service.impl;

import com.homeopathy.homeopathyclinic.model.Patient;
import com.homeopathy.homeopathyclinic.model.UserPrincipal;
import com.homeopathy.homeopathyclinic.repository.PatientRepository;
import com.homeopathy.homeopathyclinic.service.PatientService;
import io.jsonwebtoken.io.Decoders;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTServiceImpl jwtService;


    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientByEmail(String email) throws UsernameNotFoundException {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.delete(patientRepository.getReferenceById(id));
    }

    @Override
    public Patient register(@NotNull Patient patient)
            throws SQLIntegrityConstraintViolationException {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        if(patient.getRole().isEmpty())patient.setRole("USER");
        return patientRepository.save(patient);
    }



    @Override
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

    @Override
    public Optional<Patient> getPatientById(long id) {
        return patientRepository.findById(id);
    }

}
