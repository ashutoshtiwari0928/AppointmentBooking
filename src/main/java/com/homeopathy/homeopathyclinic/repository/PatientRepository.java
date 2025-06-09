package com.homeopathy.homeopathyclinic.repository;

import com.homeopathy.homeopathyclinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
