package com.homeopathy.homeopathyclinic.service.impl;

import com.homeopathy.homeopathyclinic.model.Doctor.Doctor;
import com.homeopathy.homeopathyclinic.model.DoctorLoginDetails;
import com.homeopathy.homeopathyclinic.model.Patient;
import com.homeopathy.homeopathyclinic.model.PatientLoginDetails;
import com.homeopathy.homeopathyclinic.repository.DoctorRepository;
import com.homeopathy.homeopathyclinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<Patient> patient = patientRepository.findByEmail(email);
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        if(patient.isEmpty() && doctor.isEmpty()){
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }
        else if(doctor.isPresent()){
            return new DoctorLoginDetails(doctor.get().getEmail(),doctor.get().getPassword());
        }
        return new PatientLoginDetails(patient.get().getEmail(),patient.get().getPassword());
    }

}
