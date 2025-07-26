package com.homeopathy.homeopathyclinic.repository;

import com.homeopathy.homeopathyclinic.model.Doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
}
