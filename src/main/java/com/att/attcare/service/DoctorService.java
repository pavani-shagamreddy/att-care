package com.att.attcare.service;

import java.util.List;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.att.attcare.auth.AuthenticationRequest;
import com.att.attcare.auth.AuthenticationResponse;
import com.att.attcare.controller.NotFoundException;
import com.att.attcare.dao.DoctorDao;
import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;
import com.att.attcare.repository.DoctorRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	private final DoctorDao doctorRepository;

	public DoctorService(DoctorDao doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	public List<Doctor> getAllDoctors() {
		return doctorRepository.getAll();
	}

	public Doctor getDoctorById(int id) throws NotFoundException{
		Doctor optionalDoctor = doctorRepository.getById(id);
		if(optionalDoctor!=null) {
			return optionalDoctor;
		}
		else {
			throw new NotFoundException("Doctor not found with id:"+id);
		}
	}

	public Optional<Doctor> getDoctorByEmail(String email) {
		Optional<Doctor> optionalDoctor = doctorRepository.getByEmail(email);
		return optionalDoctor;
	}

	
	  public Doctor createDoctor(Doctor doctor) { 
		 
	  Doctor doctor1=doctorRepository.savedoctor(doctor);
	  return doctor1; }
	  
	



	public ResponseEntity<Doctor> updateDoctorById(int id, Doctor updatedDoctor) {
		
    	Doctor doctor =doctorRepository.getById(id);
    	if(doctor!=null) {
    		doctor.setName(updatedDoctor.getName());
			doctor.setEmail(updatedDoctor.getEmail());
			doctor.setSpecialization(updatedDoctor.getSpecialization());
			doctor.setGender(updatedDoctor.getGender());
			doctor.setMobile(updatedDoctor.getMobile());
		Doctor updatedDoctor1=doctorRepository.savedoctor(doctor);
		return ResponseEntity.ok(updatedDoctor1);
    	}
    	else {
    		throw new NotFoundException("DOCTOR does not exists with id: "+id);
    	}
	}


	public void deleteDoctor(int id) throws NotFoundException {
		Doctor optionalDoctor = doctorRepository.getById(id);
		if (optionalDoctor!=null) {
			doctorRepository.delete(id);

		} else {
			throw new NotFoundException("doctor not found with id:" + id);
		}
	}

	// Add more service methods as needed
}
