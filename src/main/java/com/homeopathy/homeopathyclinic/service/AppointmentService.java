package com.homeopathy.homeopathyclinic.service;

import com.homeopathy.homeopathyclinic.DTO.AppointmentRequest;
import com.homeopathy.homeopathyclinic.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    Appointment bookAppointment(AppointmentRequest appointmentRequest);

    List<Appointment> getAppointmentByPatient(Long patientId);
    void cancelAppointment(Long appointmentId);
    List<Appointment> getAllAppointments();
    void addAppointmentsByDate(LocalDate date);

    List<Appointment> getAppointmentsByDate(LocalDate date);
    List<Appointment> getBookedSlots(LocalDate date);
    List<Appointment> getUnbookedSlots(LocalDate date);
}
