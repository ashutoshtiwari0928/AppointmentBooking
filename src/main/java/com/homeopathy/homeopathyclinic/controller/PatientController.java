package com.homeopathy.homeopathyclinic.controller;

import com.homeopathy.homeopathyclinic.model.Appointment;
import com.homeopathy.homeopathyclinic.model.Patient;
import com.homeopathy.homeopathyclinic.model.PatientLoginDetails;
import com.homeopathy.homeopathyclinic.service.PatientService;
import com.homeopathy.homeopathyclinic.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

//    @GetMapping
//    public List<Patient> getAllPatients(){
//        return patientService.getAllPatients();
//    }

    @GetMapping("/appointments/{email}")
    public ResponseEntity<?> getPatientByEmail(@PathVariable String email){
        System.out.println("Reached this step");
        try {
            Patient patient = patientService.getPatientByEmail(email);
            List<Appointment> appointmentList = patient.getAppointmentList();
            return new ResponseEntity<>(appointmentList,HttpStatus.OK);
        }
        catch(Exception e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id){
        Optional<Patient> p = patientService.getPatientById(id);
        System.out.println(p);
        if(p.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(p,HttpStatus.OK);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Patient patient){
        try {
            return new ResponseEntity<>(patientService.register(patient),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>
                    ("Email and Phone number should be unique",
                    HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/sendEmail")
    public ResponseEntity<?> send(@RequestParam String email,@RequestParam String password){
        System.out.println(email);
        System.out.println(password);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @PostMapping("/login")
//    public String verify(@RequestBody Patient patient){
//        return patientService.verify(patient);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletePatient(@PathVariable Long id){
//        patientService.deletePatient(id);
//    }

}
