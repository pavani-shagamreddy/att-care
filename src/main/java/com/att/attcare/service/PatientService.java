package com.att.attcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.att.attcare.dao.PatientDao;
import com.att.attcare.model.Patient;
import com.att.attcare.repository.PatientRepository;



@Service
public class PatientService {
	private final PatientDao patientRepository;

    public PatientService(PatientDao patientRepository) {
        this.patientRepository = patientRepository;
    }

 
    public List<Patient> getAllPatients() {
        return patientRepository.getAll();
    }

  
    public ResponseEntity<Patient> getPatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.getById(id);
        return optionalPatient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

 
    public ResponseEntity<Patient> createPatient(Patient patient) {
       
        Patient savedPatient = patientRepository.savePatient(patient);
        return ResponseEntity.ok(savedPatient);
    }

  
    public ResponseEntity<Patient> updatePatient(Long id, Patient updatedPatient) {
        Optional<Patient> optionalPatient = patientRepository.getById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
           patient.setName(updatedPatient.getName());
           patient.setAge(updatedPatient.getAge());
           patient.setEmail(updatedPatient.getEmail());
           patient.setGender(updatedPatient.getGender());
           patient.setMobile(updatedPatient.getMobile());
           patientRepository.savePatient(updatedPatient);
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

  
    public String deletePatient(Long id) {
        Optional<Patient> optionalPatient = patientRepository.getById(id);
        if (optionalPatient.isPresent()) {
            patientRepository.delete(id);
            return "Patient removed successfully";
        } else {
            return "Patient not found";
        }
    }

}
