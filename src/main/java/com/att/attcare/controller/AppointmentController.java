package com.att.attcare.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.attcare.dao.AppointmentRepository;
import com.att.attcare.dao.DoctorRepository;
import com.att.attcare.dao.PatientRepository;
import com.att.attcare.dao.ReceptionistRepository;
import com.att.attcare.dto.BookedTimeSlotsRequest;
import com.att.attcare.model.Appointment;
import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;
import com.att.attcare.model.Receptionist;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/attcare/appointments")
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ReceptionistRepository receptionistRepository;

    public AppointmentController(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository,  ReceptionistRepository receptionistRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository=doctorRepository;
        this.receptionistRepository=receptionistRepository;
    }

    @GetMapping("/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            return appointmentRepository.findByPatientId(patientId);
        } else {
            throw new NotFoundException("Patient not found");
        }
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
  
    	int doctorId=appointment.getDoctor().getId();
    	Optional<Doctor> optionalDoctor=doctorRepository.findById(doctorId);
    	if(optionalDoctor.isPresent()) {
    		Doctor doctor=optionalDoctor.get();
    		appointment.setDoctor(doctor);
    	}
    	else {
    		 throw new NotFoundException("Doctor not found with id:"+doctorId);
    	}
     
       Long patientId = appointment.getPatient().getId();
       
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            appointment.setPatient(patient);
            
        } else {
            throw new NotFoundException("Patient not found with id:"+patientId);
        }
        
        return appointmentRepository.save(appointment);
        
        
    }
    
    @PostMapping("/booked-slots")
    public List<LocalTime> getBookedTimeSlots(@RequestBody BookedTimeSlotsRequest req){
    	Doctor doctor=req.getDoctor();
    	List<LocalTime> bookedTimeSlotsList=new ArrayList<LocalTime>();
    	String appointmentDate=req.getAppointmentDate();
    	List<Appointment> appointments=appointmentRepository.findByDoctorAndDate(doctor,appointmentDate);
    	 for(Appointment eAppointment:appointments) {
        	 LocalTime t=eAppointment.getTime();
        	bookedTimeSlotsList.add(t);
         }
  
    	return bookedTimeSlotsList;
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

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            appointmentRepository.deleteById(id);
            return "Appointment deleted successfully";
        } else {
            return "Appointment not found";
        }
    }
}
