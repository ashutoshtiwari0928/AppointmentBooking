package com.homeopathy.homeopathyclinic.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email","phone"})
        }
)
public class Patient {
    @Id @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Date dateOfBirth;
    private String role;

    public Long getId() {
        return id;
    }
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    public List<Appointment> appointmentList;



    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        if(this.role==null){
            this.setRole("PATIENT");
            return "PATIENT";
        }
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", role='" + role + '\'' +
                '}';
    }

}
