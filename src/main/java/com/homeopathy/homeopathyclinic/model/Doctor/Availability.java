package com.homeopathy.homeopathyclinic.model.Doctor;

import java.sql.Time;
import java.util.Date;

public class Availability {
    private Long id;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int slotDuration;
}
