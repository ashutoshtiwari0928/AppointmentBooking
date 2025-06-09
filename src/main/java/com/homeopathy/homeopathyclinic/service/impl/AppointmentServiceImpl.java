package com.homeopathy.homeopathyclinic.service.impl;

import com.homeopathy.homeopathyclinic.DTO.AppointmentRequest;
import com.homeopathy.homeopathyclinic.model.Appointment;
import com.homeopathy.homeopathyclinic.model.Patient;
import com.homeopathy.homeopathyclinic.repository.AppointmentRepository;
import com.homeopathy.homeopathyclinic.repository.PatientRepository;
import com.homeopathy.homeopathyclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Appointment bookAppointment(AppointmentRequest appointmentRequest) {
        Patient patient = patientRepository.findById(appointmentRequest.getId())
                .orElseThrow(() -> new IllegalStateException("No such patient"));

        List<Appointment> allAppointments = appointmentRepo.findByDate(appointmentRequest.getDate());
        for(Appointment appointment: allAppointments){
            if(isOverlap(appointmentRequest.getTimeFrom(),
                    appointmentRequest.getTimeUpto(),
                    appointment.getTimeFrom(),
                    appointment.getTimeUpto())){
                throw new IllegalStateException( "Slot interferes with other slots. Please select another slot.");
            }
        }
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDate(appointmentRequest.getDate());
        appointment.setTimeFrom(appointmentRequest.getTimeFrom());
        appointment.setTimeUpto(appointmentRequest.getTimeUpto());
        appointment.setReason(appointmentRequest.getReason());
        return appointmentRepo.save(appointment);
    }

    private boolean isOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return (start1.isBefore(end2) && end1.isAfter(start2));
    }

    @Override
    public List<Appointment> getAppointmentByPatient(Long patientId) {
        return appointmentRepo.findAll()
                .stream()
                .filter(a -> a.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        appointmentRepo.deleteById(appointmentId);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

}
