package com.att.attcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	Doctor findById(int id);

	Optional<Doctor> findByEmail(String email);

	void deleteById(Long id);
}
