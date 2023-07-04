package com.att.attcare.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.attcare.dao.AppointmentRepository;
import com.att.attcare.dao.DoctorRepository;
import com.att.attcare.dao.PatientRepository;
import com.att.attcare.dao.ReceptionistRepository;
import com.att.attcare.model.Appointment;
import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/attcare/doctor/appointment")
@PreAuthorize("hasRole('DOCTOR')")
public class UpdateAppointmentController {

	    private final AppointmentRepository appointmentRepository;
	    private final PatientRepository patientRepository;
	    private final DoctorRepository doctorRepository;

	    public UpdateAppointmentController(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
	        this.appointmentRepository = appointmentRepository;
	        this.patientRepository = patientRepository;
	        this.doctorRepository=doctorRepository;
	    }

	@PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable  Long id, @RequestBody @Valid Appointment updatedAppointment) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setDate(updatedAppointment.getDate());
            appointment.setTime(updatedAppointment.getTime());
            appointment.setProblem(updatedAppointment.getProblem());
            appointment.setPrescription(updatedAppointment.getPrescription());
            appointment.setIncomingDate(updatedAppointment.getIncomingDate());
            appointment.setIncomingTime(updatedAppointment.getIncomingTime());
            appointment.setOutgoingDate(updatedAppointment.getOutgoingDate());
            appointment.setOutgoingTime(updatedAppointment.getOutgoingTime());
        	int doctorId=updatedAppointment.getDoctor().getId();
        	Optional<Doctor> optionalDoctor=doctorRepository.findById(doctorId);
        	if(optionalDoctor.isPresent()) {
        		Doctor doctor=optionalDoctor.get();
        		appointment.setDoctor(doctor);
        	}
        	else {
        		 throw new NotFoundException("Doctor not found with id:"+doctorId);
        	}
         
            
            Long patientId = updatedAppointment.getPatient().getId();
            Optional<Patient> optionalPatient = patientRepository.findById(patientId);
            if (optionalPatient.isPresent()) {
                Patient patient = optionalPatient.get();
                appointment.setPatient(patient);
                appointmentRepository.save(appointment);
                return ResponseEntity.ok(appointment);
            } else {
                throw new NotFoundException("Patient not found with id:"+patientId);
            }
            
         
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
