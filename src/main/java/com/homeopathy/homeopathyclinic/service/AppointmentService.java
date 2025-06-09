package com.homeopathy.homeopathyclinic.service;

import com.homeopathy.homeopathyclinic.DTO.AppointmentRequest;
import com.homeopathy.homeopathyclinic.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(AppointmentRequest appointment);
    List<Appointment> getAppointmentByPatient(Long patientId);
    void cancelAppointment(Long appointmentId);
    List<Appointment> getAllAppointments();
}
