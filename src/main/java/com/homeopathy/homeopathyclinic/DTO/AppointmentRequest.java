package com.homeopathy.homeopathyclinic.DTO;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Component
public class AppointmentRequest {
    private Long id;
    private LocalDate date;
    private LocalTime timeFrom;
    private LocalTime timeUpto;
    private String reason;

    public Long getId() {
        return id;
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

    public LocalTime getTimeUpto() {
        return timeUpto;
    }
}
