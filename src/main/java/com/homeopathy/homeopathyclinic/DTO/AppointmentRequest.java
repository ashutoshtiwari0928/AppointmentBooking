package com.homeopathy.homeopathyclinic.DTO;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class AppointmentRequest {
    private Long id;
    private LocalDate date;
    private LocalTime timeFrom;
    private LocalTime timeUpto;
    private Boolean booked;
    private String reason;



    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public String getReason() {
        return reason;
    }

    public LocalTime getTimeUpto() {
        return timeUpto;
    }

    public Boolean getBooked() {
        return booked;
    }

    public Long getId() {
        return id;
    }
}
