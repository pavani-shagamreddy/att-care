package com.att.attcare.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.attcare.model.Patient;
import com.att.attcare.repository.PatientRepository;
import com.att.attcare.service.PatientService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/attcare/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

   
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
       return patientService.getPatientById(id);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody @Valid Patient patient) {
     return patientService.createPatient(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody @Valid Patient updatedPatient) {
       return patientService.updatePatient(id, updatedPatient);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {
     return patientService.deletePatient(id);
    }
}
