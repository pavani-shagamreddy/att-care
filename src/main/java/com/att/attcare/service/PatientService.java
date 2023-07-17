package com.att.attcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.att.attcare.controller.NotFoundException;
import com.att.attcare.dao.PatientDao;
import com.att.attcare.model.Doctor;
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

  
    public Optional<Patient> getPatientById(Long id) throws NotFoundException{
        Optional<Patient> optionalPatient = patientRepository.getById(id);
       if(optionalPatient.isPresent()) {
    	   return optionalPatient;
       }
       else {
    	   throw new NotFoundException("Patient not found with id:"+id);
       }
    }

 
    public ResponseEntity<Patient> createPatient(Patient patient) {
       
        Patient savedPatient = patientRepository.savePatient(patient);
        return ResponseEntity.ok(savedPatient);
    }

  
  public ResponseEntity<Patient> updatePatient(Long id, Patient updatedPatient) throws NotFoundException {
    Optional<Patient> patientOptional = patientRepository.getById(id);
    
    if (patientOptional.isPresent()) {
        Patient patient = patientOptional.get();
        
        // Check if the patient's ID matches the provided ID
        if (patient.getId().equals(id)) {
            patient.setName(updatedPatient.getName());
            patient.setAge(updatedPatient.getAge());
            patient.setEmail(updatedPatient.getEmail());
            patient.setGender(updatedPatient.getGender());
            patient.setMobile(updatedPatient.getMobile());
            
            Patient updatedPatient1 = patientRepository.savePatient(patient);
            return ResponseEntity.ok(updatedPatient1);
        }
        else {
            throw new NotFoundException("Patient does not exist with id: " + id);
        }
    }
    else {
        throw new NotFoundException("Patient does not exist with id: " + id);
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
