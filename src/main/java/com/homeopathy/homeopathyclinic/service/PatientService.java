package com.homeopathy.homeopathyclinic.service;

import com.homeopathy.homeopathyclinic.model.Patient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient getPatientByEmail(String email) throws UsernameNotFoundException;
    public void deletePatient(Long id);
    public Patient addNewPatient(Patient patient);
    public UserDetails loadUserByUsername(String email);
}
