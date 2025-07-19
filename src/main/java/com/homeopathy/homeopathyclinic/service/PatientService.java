package com.homeopathy.homeopathyclinic.service;

import com.homeopathy.homeopathyclinic.model.Patient;
import com.homeopathy.homeopathyclinic.model.UserPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient getPatientByEmail(String email) throws UsernameNotFoundException;
    public void deletePatient(Long id);
    public Patient register(Patient patient) throws SQLIntegrityConstraintViolationException;

    String verifyEmail(String email, String password);

    Optional<Patient> getPatientById(long id);
}
