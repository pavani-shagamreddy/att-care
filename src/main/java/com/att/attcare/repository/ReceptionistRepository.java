package com.att.attcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;
import com.att.attcare.model.Receptionist;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer> {

	Optional<Receptionist> findById(Long id);

	Receptionist findByEmail(String email);

	void deleteById(Long id);

	
}
