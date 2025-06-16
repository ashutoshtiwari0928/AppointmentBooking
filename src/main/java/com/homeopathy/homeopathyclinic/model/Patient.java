package com.homeopathy.homeopathyclinic.model;

import jakarta.persistence.*;

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
    private String name;
    private String email;
    private String password;
    private String phone;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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
}
