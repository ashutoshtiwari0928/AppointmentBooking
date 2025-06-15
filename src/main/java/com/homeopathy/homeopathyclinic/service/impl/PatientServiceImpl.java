package com.homeopathy.homeopathyclinic.service.impl;

import com.homeopathy.homeopathyclinic.model.Patient;
import com.homeopathy.homeopathyclinic.repository.PatientRepository;
import com.homeopathy.homeopathyclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.getReferenceById(id);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.delete(patientRepository.getReferenceById(id));
    }

    @Override
    public Patient addNewPatient(Patient patient){
        return patientRepository.save(patient);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find username"));
        return User.builder()
                .username(patient.getEmail())
//                .password(patient.getPassword())
                .roles("PATIENT")
                .build();
    }

}
