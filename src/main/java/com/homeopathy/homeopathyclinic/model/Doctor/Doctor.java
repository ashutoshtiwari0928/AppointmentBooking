package com.homeopathy.homeopathyclinic.model.Doctor;

import com.homeopathy.homeopathyclinic.model.Appointment;
import com.homeopathy.homeopathyclinic.model.Reviews;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;


@Entity
@Table(
        name = "doctor",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email","phone"})
        }
)
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;
    private String about;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private ArrayList<Certification> certifications;
    private ArrayList<Education> educations;
    private ArrayList<Specialization> specializations;
    private ArrayList<Reviews> reviews;
    private ArrayList<Availability> availableSlots;
    @OneToOne(mappedBy = "doctor",cascade = CascadeType.ALL, orphanRemoval = true)
    private Appointment appointment;
    private String role;

    public String getAbout() {
        return about;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Certification> getCertifications() {
        return certifications;
    }

    public ArrayList<Education> getEducations() {
        return educations;
    }

    public ArrayList<Specialization> getSpecializations() {
        return specializations;
    }

    public ArrayList<Reviews> getReviews() {
        return reviews;
    }

    public ArrayList<Availability> getAvailableSlots() {
        return availableSlots;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password =  password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String Role) {
        this.role = role;
    }
}
