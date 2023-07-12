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

	public ResponseEntity<Doctor> getDoctorById(int id) {
		Optional<Doctor> optionalDoctor = doctorRepository.getById(id);
		return optionalDoctor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	public Optional<Doctor> getDoctorByEmail(String email) {
		Optional<Doctor> optionalDoctor = doctorRepository.getByEmail(email);
		return optionalDoctor;
	}

	
	  public Doctor createDoctor(Doctor doctor) { 
		 
	  Doctor doctor1=doctorRepository.savedoctor(doctor);
	  return doctor1; }
	  
	  
	/*
	 * public ResponseEntity<Doctor> updateDoctor(int id, Doctor updatedDoctor) {
	 * Optional<Doctor> optionalDoctor = doctorRepository.findById(id); if
	 * (optionalDoctor.isPresent()) { updatedDoctor.setId(id);
	 * 
	 * Doctor doctor = optionalDoctor.get();
	 * doctor.setName(updatedDoctor.getName());
	 * doctor.setEmail(updatedDoctor.getEmail());
	 * doctor.setPassword(updatedDoctor.getPassword());
	 * doctor.setSpecialization(updatedDoctor.getSpecialization());
	 * doctor.setGender(updatedDoctor.getGender());
	 * doctor.setMobile(updatedDoctor.getMobile());
	 * doctor.setExperience(updatedDoctor.getExperience());
	 * 
	 * doctorRepository.save(updatedDoctor); return
	 * ResponseEntity.ok(updatedDoctor); } else { return
	 * ResponseEntity.notFound().build(); } }
	 */
	 

	public ResponseEntity<Doctor> updateDoctorByEmail(String email, Doctor updatedDoctor) {
		Optional<Doctor> optionalDoctor = doctorRepository.getByEmail(email);
		if (optionalDoctor.isPresent()) {
			Doctor doctor = optionalDoctor.get();
			doctor.setName(updatedDoctor.getName());
			doctor.setEmail(updatedDoctor.getEmail());
			doctor.setSpecialization(updatedDoctor.getSpecialization());
			doctor.setGender(updatedDoctor.getGender());
			doctor.setMobile(updatedDoctor.getMobile());
			doctorRepository.savedoctor(updatedDoctor);
			return ResponseEntity.ok(doctor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public void deleteDoctor(int id) throws NotFoundException {
		Optional<Doctor> optionalDoctor = doctorRepository.getById(id);
		if (optionalDoctor.isPresent()) {
			doctorRepository.delete(id);

		} else {
			throw new NotFoundException("doctor not found with id:" + id);
		}
	}

	// Add more service methods as needed
}
