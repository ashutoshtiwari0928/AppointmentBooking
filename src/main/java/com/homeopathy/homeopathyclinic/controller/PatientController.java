package com.homeopathy.homeopathyclinic.controller;

import com.homeopathy.homeopathyclinic.model.Patient;
import com.homeopathy.homeopathyclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }
    @GetMapping("/{email}")
    public ResponseEntity<?> getPatientByEmail(@PathVariable String email){
        try {
            return new ResponseEntity<>(patientService.getPatientByEmail(email),HttpStatus.OK);
        }
        catch(Exception e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/register")
    public Patient addNewPatient(@RequestBody Patient patient){
        return patientService.addNewPatient(patient);
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
    }

}
