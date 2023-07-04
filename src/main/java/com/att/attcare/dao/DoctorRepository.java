package com.att.attcare.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	Optional<Doctor> findById(Long id);
	/*
	 * Optional<Doctor> findById(Long id); Optional<Doctor> deleteById(Long id);
	 * Optional<Doctor> findByEmail(String email);
	 */

	Optional<Doctor> findByEmail(String email);

	void deleteById(Long id);
}
