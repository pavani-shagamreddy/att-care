package com.att.attcare.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.att.attcare.model.Doctor;
import com.att.attcare.repository.DoctorRepository;

@Component
public class DoctorDao {
	private final DoctorRepository doctorRepository;

	public DoctorDao(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	public List<Doctor> getAll() {
		return doctorRepository.findAll();
	}

	public Doctor getById(int doctorId) {
		// TODO Auto-generated method stub
		return  doctorRepository.findById(doctorId);
	}

	public Optional<Doctor> getByEmail(String email) {
		// TODO Auto-generated method stub
		return  doctorRepository.findByEmail(email);
	}

	public Doctor savedoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return  doctorRepository.save(doctor);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		 doctorRepository.deleteById(id);
	}


}
