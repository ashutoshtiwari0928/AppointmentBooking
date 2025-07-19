package com.homeopathy.homeopathyclinic.service.impl;

import com.homeopathy.homeopathyclinic.model.Patient;
import com.homeopathy.homeopathyclinic.model.UserPrincipal;
import com.homeopathy.homeopathyclinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<Patient> patient = patientRepository.findByEmail(email);
        if(patient.isEmpty()){
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(patient.get().getEmail(),patient.get().getPassword());
    }

}
