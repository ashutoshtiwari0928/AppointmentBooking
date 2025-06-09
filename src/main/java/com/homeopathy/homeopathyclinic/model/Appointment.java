package com.homeopathy.homeopathyclinic.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Patient patient;

    @NonNull
    private LocalDate date;
    @NonNull
    private LocalTime timeFrom;
    @NonNull
    private LocalTime timeUpto;
    private String reason;

    public Appointment() {
    }



    public Patient getPatient() {
        return patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }


    public String getReason() {
        return reason;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalTime getTimeUpto() {
        return timeUpto;
    }

    public void setTimeUpto(LocalTime timeUpto) {
        this.timeUpto = timeUpto;
    }
}