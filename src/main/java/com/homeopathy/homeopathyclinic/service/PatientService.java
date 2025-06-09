package com.homeopathy.homeopathyclinic.service;

import com.homeopathy.homeopathyclinic.model.Patient;

import java.util.List;

public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient getPatientById(Long id);
    public void deletePatient(Long id);
    public Patient addNewPatient(Patient patient);
}
