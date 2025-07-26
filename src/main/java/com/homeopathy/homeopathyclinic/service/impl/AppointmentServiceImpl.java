package com.homeopathy.homeopathyclinic.service.impl;

import com.homeopathy.homeopathyclinic.DTO.AppointmentRequest;
import com.homeopathy.homeopathyclinic.model.Appointment;
import com.homeopathy.homeopathyclinic.repository.AppointmentRepository;
import com.homeopathy.homeopathyclinic.repository.PatientRepository;
import com.homeopathy.homeopathyclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Appointment bookAppointment(AppointmentRequest appointmentRequest) {
        List<Appointment> list = appointmentRepo.findByDate(appointmentRequest.getDate());
        if(list.isEmpty()){
            throw new IllegalStateException("No slots available on this date. ");
        }
        else{
            for(Appointment appointment: list){
                if((appointmentRequest.getTimeFrom().equals(appointment.getTimeFrom()))
                        && (appointmentRequest.getTimeUpto().equals(appointment.getTimeUpto()))
                ){
                    appointment.setStatus(true);

//                    appointment.setPatient(patientRepository.getReferenceById(appointmentRequest.getId()));
                    appointment.setReason(appointmentRequest.getReason());
                    appointmentRepo.save(appointment);
                    return appointment;
                }
            }
        }
        throw new IllegalStateException("No such slot found.");
    }

    private boolean isOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return (start1.isBefore(end2) && end1.isAfter(start2));
    }
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    @Override
    public void addAppointmentsByDate(LocalDate date) {
        add40minutesSlots(date,LocalTime.of(9,0),LocalTime.of(13,0));
        add40minutesSlots(date,LocalTime.of(14,0),LocalTime.of(18,0));
    }



    public void add40minutesSlots(LocalDate date, LocalTime timeFrom,LocalTime timeUpto){
        for(LocalTime time=timeFrom;!time.isAfter(timeUpto);time=time.plusMinutes(45)){
            LocalTime start = time;
            LocalTime end = time.plusMinutes(40);
            Appointment appointment = new Appointment();
            appointment.setDate(date);
            appointment.setTimeFrom(start);
            appointment.setTimeUpto(end);
            appointment.setStatus(false);
            appointmentRepo.save(appointment);
        }
    }

}
