package com.att.attcare.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.att.attcare.model.Patient;
import com.att.attcare.repository.PatientRepository;

@Component
public class PatientDao {
	private final PatientRepository patientRepository;

    public PatientDao(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

	public List<Patient> getAll() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	public Optional<Patient> getById(Long id) {
		// TODO Auto-generated method stub
		return  patientRepository.findById(id);
	}

	public Patient savePatient(Patient patient) {
		// TODO Auto-generated method stub
		return  patientRepository.save(patient);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		 patientRepository.deleteById(id);
	}

 
}
