package com.homeopathy.homeopathyclinic.controller;

import com.homeopathy.homeopathyclinic.DTO.AppointmentRequest;
import com.homeopathy.homeopathyclinic.model.Appointment;
import com.homeopathy.homeopathyclinic.service.AppointmentService;
import com.twilio.rest.microvisor.v1.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


    @PostMapping("/default/{date}")
    public void addAppointmentsOnADate(@PathVariable
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                   LocalDate date){
        appointmentService.addAppointmentsByDate(date);
    }
//    @GetMapping("/{date}")
//    public List<Appointment> getAppointmentsByDate(@PathVariable
//                                                       @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
//                                                               LocalDate date){
//        return appointmentService.getAppointmentsByDate(date);
//    }
//    @GetMapping("/{date}/booked")
//    public List<Appointment> getBookedAppointmentsByDate(@PathVariable
//                                                         @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
//                                                         LocalDate date){
//        return appointmentService.getBookedSlots(date);
//    }
//    @GetMapping("/{date}/unbooked")
//    public List<Appointment> getUnbookedAppointmentsByDate(@PathVariable
//                                                         @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
//                                                                 LocalDate date){
//        return appointmentService.getUnbookedSlots(date);
//    }


}
