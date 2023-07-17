package com.att.attcare.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.attcare.dto.BookedTimeSlotsRequest;
import com.att.attcare.model.Appointment;
import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;
import com.att.attcare.model.Receptionist;
import com.att.attcare.repository.AppointmentRepository;
import com.att.attcare.repository.DoctorRepository;
import com.att.attcare.repository.PatientRepository;
import com.att.attcare.repository.ReceptionistRepository;
import com.att.attcare.service.AppointmentService;
import com.att.attcare.service.DoctorService;
import com.att.attcare.service.PatientService;
import com.att.attcare.service.ReceptionistService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attcare/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    
    
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
     }

    @GetMapping("/{date}")
    public List<Appointment> getAppointmentsByDate(@PathVariable String date){
    	return appointmentService.getAppointmentsByDate(date);
    }
    
  
    @GetMapping("/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long patientId) {
       return appointmentService.getAppointmentsByPatientId(patientId);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
      
    	return appointmentService.createAppointment(appointment);
        
    }
    
    @PostMapping("/booked-slots")
    public List<LocalTime> getBookedTimeSlots(@RequestBody BookedTimeSlotsRequest req){
    	return appointmentService.getBookedTimeSlots(req);
    }
  
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable  Long id, @RequestBody @Valid Appointment updatedAppointment) {
    	return appointmentService.updateAppointment(id, updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id) {
    	return appointmentService.deleteAppointment(id);
    }
}
