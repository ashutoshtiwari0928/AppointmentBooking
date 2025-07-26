package com.homeopathy.homeopathyclinic.service;

import com.homeopathy.homeopathyclinic.model.Patient;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient getPatientByEmail(String email) throws UsernameNotFoundException;
    public void deletePatient(Long id);
    public Patient register(Patient patient) throws SQLIntegrityConstraintViolationException;


    Optional<Patient> getPatientById(long id);
}
