package com.att.attcare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.att.attcare.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
