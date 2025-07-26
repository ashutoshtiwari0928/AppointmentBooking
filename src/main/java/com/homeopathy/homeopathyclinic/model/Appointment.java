package com.homeopathy.homeopathyclinic.model;

import com.homeopathy.homeopathyclinic.model.Doctor.Doctor;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(
        name = "appointment",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"})
        }
)
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private LocalDate date;
    @NonNull
    private LocalTime timeFrom;
    @NonNull
    private LocalTime timeUpto;
    private Boolean status;
    private String reason;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    public Appointment() {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean booked) {
        this.status = booked;
    }

    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}