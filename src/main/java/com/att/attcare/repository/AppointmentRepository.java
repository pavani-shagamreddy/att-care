package com.att.attcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.att.attcare.model.Appointment;
import com.att.attcare.model.Doctor;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);

	List<Appointment> findByDoctorAndDate(Doctor doctor, String appointmentDate);


	List<Appointment> findByDoctor(Doctor doctor);

	List<Appointment> findByDate(String date);
}

