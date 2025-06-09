package com.homeopathy.homeopathyclinic.repository;

import com.homeopathy.homeopathyclinic.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment>  findByPatientId(Long patientId);

    List<Appointment> findByDateAndTimeFromAndTimeUpto(LocalDate date,
                                                       LocalTime timeFrom,
                                                       LocalTime timeUpto);

    List<Appointment> findByDate(LocalDate date);
}
