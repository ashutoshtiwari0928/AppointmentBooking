warning: in the working copy of 'src/main/resources/application.properties', LF will be replaced by CRLF the next time Git touches it
[1mdiff --git a/src/main/java/com/homeopathy/homeopathyclinic/DTO/AppointmentRequest.java b/src/main/java/com/homeopathy/homeopathyclinic/DTO/AppointmentRequest.java[m
[1mindex 362b2a0..4312b19 100644[m
[1m--- a/src/main/java/com/homeopathy/homeopathyclinic/DTO/AppointmentRequest.java[m
[1m+++ b/src/main/java/com/homeopathy/homeopathyclinic/DTO/AppointmentRequest.java[m
[36m@@ -14,9 +14,7 @@[m [mpublic class AppointmentRequest {[m
     private Boolean booked;[m
     private String reason;[m
 [m
[31m-    public Long getId() {[m
[31m-        return id;[m
[31m-    }[m
[32m+[m
 [m
     public LocalDate getDate() {[m
         return date;[m
[36m@@ -37,4 +35,8 @@[m [mpublic class AppointmentRequest {[m
     public Boolean getBooked() {[m
         return booked;[m
     }[m
[32m+[m
[32m+[m[32m    public Long getId() {[m
[32m+[m[32m        return id;[m
[32m+[m[32m    }[m
 }[m
[1mdiff --git a/src/main/java/com/homeopathy/homeopathyclinic/controller/PatientController.java b/src/main/java/com/homeopathy/homeopathyclinic/controller/PatientController.java[m
[1mindex 70b1657..52bd52a 100644[m
[1m--- a/src/main/java/com/homeopathy/homeopathyclinic/controller/PatientController.java[m
[1m+++ b/src/main/java/com/homeopathy/homeopathyclinic/controller/PatientController.java[m
[36m@@ -3,6 +3,8 @@[m [mpackage com.homeopathy.homeopathyclinic.controller;[m
 import com.homeopathy.homeopathyclinic.model.Patient;[m
 import com.homeopathy.homeopathyclinic.service.PatientService;[m
 import org.springframework.beans.factory.annotation.Autowired;[m
[32m+[m[32mimport org.springframework.http.HttpStatus;[m
[32m+[m[32mimport org.springframework.http.ResponseEntity;[m
 import org.springframework.security.core.parameters.P;[m
 import org.springframework.web.bind.annotation.*;[m
 [m
[36m@@ -18,11 +20,16 @@[m [mpublic class PatientController {[m
     public List<Patient> getAllPatients(){[m
         return patientService.getAllPatients();[m
     }[m
[31m-    @GetMapping("/{id}")[m
[31m-    public Patient getPatientById(@PathVariable Long id){[m
[31m-        return patientService.getPatientById(id);[m
[32m+[m[32m    @GetMapping("/{email}")[m
[32m+[m[32m    public ResponseEntity<?> getPatientByEmail(@PathVariable String email){[m
[32m+[m[32m        try {[m
[32m+[m[32m            return new ResponseEntity<>(patientService.getPatientByEmail(email),HttpStatus.OK);[m
[32m+[m[32m        }[m
[32m+[m[32m        catch(Exception e) {[m
[32m+[m[32m                return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);[m
[32m+[m[32m        }[m
     }[m
[31m-    @PostMapping()[m
[32m+[m[32m    @PostMapping("/register")[m
     public Patient addNewPatient(@RequestBody Patient patient){[m
         return patientService.addNewPatient(patient);[m
     }[m
[1mdiff --git a/src/main/java/com/homeopathy/homeopathyclinic/model/Patient.java b/src/main/java/com/homeopathy/homeopathyclinic/model/Patient.java[m
[1mindex c2f9cce..d8603a7 100644[m
[1m--- a/src/main/java/com/homeopathy/homeopathyclinic/model/Patient.java[m
[1m+++ b/src/main/java/com/homeopathy/homeopathyclinic/model/Patient.java[m
[36m@@ -1,17 +1,22 @@[m
 package com.homeopathy.homeopathyclinic.model;[m
 [m
[31m-import jakarta.persistence.Entity;[m
[31m-import jakarta.persistence.GeneratedValue;[m
[31m-import jakarta.persistence.Id;[m
[32m+[m[32mimport jakarta.persistence.*;[m
 [m
 @Entity[m
[32m+[m[32m@Table([m
[32m+[m[32m        name = "patient",[m
[32m+[m[32m        uniqueConstraints = {[m
[32m+[m[32m                @UniqueConstraint(columnNames = {"email","phone"})[m
[32m+[m[32m        }[m
[32m+[m[32m)[m
 public class Patient {[m
     @Id @GeneratedValue[m
     private Long id;[m
     private String name;[m
     private String email;[m
[31m-//    private String password;[m
[32m+[m[32m    private String password;[m
     private String phone;[m
[32m+[m
     public Long getId() {[m
         return id;[m
     }[m
[36m@@ -28,11 +33,11 @@[m [mpublic class Patient {[m
         return phone;[m
     }[m
 [m
[31m-//    public String getPassword() {[m
[31m-//        return password;[m
[31m-//    }[m
[32m+[m[32m    public String getPassword() {[m
[32m+[m[32m        return password;[m
[32m+[m[32m    }[m
 [m
[31m-//    public void setPassword(String password) {[m
[31m-//        this.password = password;[m
[31m-//    }[m
[32m+[m[32m    public void setPassword(String password) {[m
[32m+[m[32m        this.password = password;[m
[32m+[m[32m    }[m
 }[m
[1mdiff --git a/src/main/java/com/homeopathy/homeopathyclinic/security/SecutiryConfig.java b/src/main/java/com/homeopathy/homeopathyclinic/security/SecutiryConfig.java[m
[1mindex cb74b28..9f9e8ec 100644[m
[1m--- a/src/main/java/com/homeopathy/homeopathyclinic/security/SecutiryConfig.java[m
[1m+++ b/src/main/java/com/homeopathy/homeopathyclinic/security/SecutiryConfig.java[m
[36m@@ -1,5 +1,7 @@[m
 package com.homeopathy.homeopathyclinic.config;[m
 [m
[32m+[m[32mimport com.homeopathy.homeopathyclinic.service.impl.PatientServiceImpl;[m
[32m+[m[32mimport com.homeopathy.homeopathyclinic.service.impl.UserDetailsServiceImpl;[m
 import org.springframework.beans.factory.annotation.Autowired;[m
 import org.springframework.cglib.proxy.NoOp;[m
 import org.springframework.context.annotation.Bean;[m
[36m@@ -35,38 +37,26 @@[m [mimport java.util.Iterator;[m
 @EnableWebSecurity[m
 public class SecutiryConfig {[m
     @Bean[m
[31m-    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{[m
[32m+[m[32m    public SecurityFilterChain filterChain(HttpSecurity http,[m
[32m+[m[32m                                           DaoAuthenticationProvider daoAuthenticationProvider)[m
[32m+[m[32m            throws Exception{[m
         http[m
[32m+[m[32m                .authenticationProvider(daoAuthenticationProvider)[m
                 .csrf(AbstractHttpConfigurer::disable)[m
                 .authorizeHttpRequests(auth -> auth[m
[32m+[m[32m                        .requestMatchers("/api/patients/register").permitAll()[m
                         .anyRequest().authenticated()[m
                 )[m
                 .formLogin(Customizer.withDefaults())[m
                 .httpBasic(Customizer.withDefaults());[m
         return http.build();[m
     }[m
[31m-[m
     @Bean[m
[31m-    public UserDetailsService userDetailsService(){[m
[31m-        UserDetails user1 = User[m
[31m-                .withUsername("Ashutosh")[m
[31m-                .password(passwordEncoder().encode("Tiwari"))[m
[31m-                .roles("USER")[m
[31m-                .build();[m
[31m-        UserDetails user2 = User[m
[31m-                .withUsername("Shakila")[m
[31m-                .password(passwordEncoder().encode("Tiwari"))[m
[31m-                .roles("ADMIN")[m
[31m-                .build();[m
[31m-[m
[31m-        return new InMemoryUserDetailsManager(user1,user2);[m
[32m+[m[32m    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService){[m
[32m+[m[32m        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);[m
[32m+[m[32m        provider.setPasswordEncoder(passwordEncoder());[m
[32m+[m[32m        return provider;[m
     }[m
[31m-//    @Bean[m
[31m-//    public AuthenticationProvider authenticationProvider(){[m
[31m-//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService());[m
[31m-//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());[m
[31m-//        return provider;[m
[31m-//    }[m
 [m
 //    @Bean[m
 //    public WebMvcConfigurer corsConfigurer() {[m
[1mdiff --git a/src/main/java/com/homeopathy/homeopathyclinic/service/PatientService.java b/src/main/java/com/homeopathy/homeopathyclinic/service/PatientService.java[m
[1mindex c93409e..5352b41 100644[m
[1m--- a/src/main/java/com/homeopathy/homeopathyclinic/service/PatientService.java[m
[1m+++ b/src/main/java/com/homeopathy/homeopathyclinic/service/PatientService.java[m
[36m@@ -2,12 +2,13 @@[m [mpackage com.homeopathy.homeopathyclinic.service;[m
 [m
 import com.homeopathy.homeopathyclinic.model.Patient;[m
 import org.springframework.security.core.userdetails.UserDetails;[m
[32m+[m[32mimport org.springframework.security.core.userdetails.UsernameNotFoundException;[m
 [m
 import java.util.List;[m
 [m
 public interface PatientService {[m
     public List<Patient> getAllPatients();[m
[31m-    public Patient getPatientById(Long id);[m
[32m+[m[32m    public Patient getPatientByEmail(String email) throws UsernameNotFoundException;[m
     public void deletePatient(Long id);[m
     public Patient addNewPatient(Patient patient);[m
     public UserDetails loadUserByUsername(String email);[m
[1mdiff --git a/src/main/java/com/homeopathy/homeopathyclinic/service/impl/PatientServiceImpl.java b/src/main/java/com/homeopathy/homeopathyclinic/service/impl/PatientServiceImpl.java[m
[1mindex bff3c95..1637597 100644[m
[1m--- a/src/main/java/com/homeopathy/homeopathyclinic/service/impl/PatientServiceImpl.java[m
[1m+++ b/src/main/java/com/homeopathy/homeopathyclinic/service/impl/PatientServiceImpl.java[m
[36m@@ -6,15 +6,20 @@[m [mimport com.homeopathy.homeopathyclinic.service.PatientService;[m
 import org.springframework.beans.factory.annotation.Autowired;[m
 import org.springframework.security.core.userdetails.User;[m
 import org.springframework.security.core.userdetails.UserDetails;[m
[32m+[m[32mimport org.springframework.security.core.userdetails.UserDetailsService;[m
 import org.springframework.security.core.userdetails.UsernameNotFoundException;[m
[32m+[m[32mimport org.springframework.security.crypto.password.PasswordEncoder;[m
 import org.springframework.stereotype.Service;[m
 [m
 import java.util.List;[m
[32m+[m[32mimport java.util.function.Supplier;[m
 [m
 @Service[m
[31m-public class PatientServiceImpl implements PatientService {[m
[32m+[m[32mpublic class PatientServiceImpl implements PatientService, UserDetailsService {[m
     @Autowired[m
     private PatientRepository patientRepository;[m
[32m+[m[32m    @Autowired[m
[32m+[m[32m    private PasswordEncoder passwordEncoder;[m
 [m
     @Override[m
     public List<Patient> getAllPatients() {[m
[36m@@ -22,8 +27,9 @@[m [mpublic class PatientServiceImpl implements PatientService {[m
     }[m
 [m
     @Override[m
[31m-    public Patient getPatientById(Long id) {[m
[31m-        return patientRepository.getReferenceById(id);[m
[32m+[m[32m    public Patient getPatientByEmail(String email) throws UsernameNotFoundException {[m
[32m+[m[32m        return patientRepository.findByEmail(email)[m
[32m+[m[32m                .orElseThrow(() -> new UsernameNotFoundException("No such user"));[m
     }[m
 [m
     @Override[m
[36m@@ -33,6 +39,7 @@[m [mpublic class PatientServiceImpl implements PatientService {[m
 [m
     @Override[m
     public Patient addNewPatient(Patient patient){[m
[32m+[m[32m        patient.setPassword(passwordEncoder.encode(patient.getPassword()));[m
         return patientRepository.save(patient);[m
     }[m
 [m
[36m@@ -42,8 +49,8 @@[m [mpublic class PatientServiceImpl implements PatientService {[m
                 .orElseThrow(() -> new UsernameNotFoundException("Could not find username"));[m
         return User.builder()[m
                 .username(patient.getEmail())[m
[31m-//                .password(patient.getPassword())[m
[31m-                .roles("PATIENT")[m
[32m+[m[32m                .password((patient.getPassword()))[m
[32m+[m[32m                .roles("USER")[m
                 .build();[m
     }[m
 [m
[1mdiff --git a/src/main/resources/application.properties b/src/main/resources/application.properties[m
[1mindex 65acf65..b05c00f 100644[m
[1m--- a/src/main/resources/application.properties[m
[1m+++ b/src/main/resources/application.properties[m
[36m@@ -4,6 +4,4 @@[m [mspring.datasource.username=root[m
 spring.datasource.password=Bajaj28@[m
 spring.jpa.hibernate.ddl-auto=update[m
 spring.jpa.show-sql=true[m
[31m-server.port=8089[m
[31m-[m
[31m-server.address=0.0.0.0[m
\ No newline at end of file[m
[32m+[m[32mserver.port=8089[m
\ No newline at end of file[m
