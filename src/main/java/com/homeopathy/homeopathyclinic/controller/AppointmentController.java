package com.homeopathy.homeopathyclinic.controller;

import com.homeopathy.homeopathyclinic.DTO.AppointmentRequest;
import com.homeopathy.homeopathyclinic.model.Appointment;
import com.homeopathy.homeopathyclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @GetMapping
    public List<Appointment> seeAllAppointments(){
        return appointmentService.getAllAppointments();
    }
    @PostMapping
    public ResponseEntity<?> book(@RequestBody AppointmentRequest appointment){
        try{
            Appointment saved = appointmentService.bookAppointment(appointment);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalStateException e){
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.CONFLICT).
                    body(e.getMessage());
        }
    }
    @GetMapping("/patients/{id}")
    public List<Appointment> showAppointmentById(@PathVariable Long id){
        return appointmentService.getAppointmentByPatient(id);
    }
    @DeleteMapping("/patients/{id}")
    public void cancelAppointment(@PathVariable Long id){
        appointmentService.cancelAppointment(id);
    }

}
