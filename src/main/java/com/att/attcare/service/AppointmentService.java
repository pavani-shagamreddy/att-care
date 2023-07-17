package com.att.attcare.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.att.attcare.controller.NotFoundException;
import com.att.attcare.dao.AppointmentDao;
import com.att.attcare.dao.DoctorDao;
import com.att.attcare.dao.PatientDao;
import com.att.attcare.dao.ReceptionistDao;
import com.att.attcare.dto.BookedTimeSlotsRequest;
import com.att.attcare.model.Appointment;
import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;
import com.att.attcare.repository.AppointmentRepository;
import com.att.attcare.repository.DoctorRepository;
import com.att.attcare.repository.PatientRepository;
import com.att.attcare.repository.ReceptionistRepository;

import jakarta.validation.Valid;

@Service
public class AppointmentService {
	  private final AppointmentDao appointmentRepository;
	    private final PatientDao patientRepository;
	    private final DoctorDao doctorRepository;
	    private final ReceptionistDao receptionistRepository;

	    public AppointmentService(AppointmentDao appointmentRepository, PatientDao patientRepository, DoctorDao doctorRepository,  ReceptionistDao receptionistRepository) {
	        this.appointmentRepository = appointmentRepository;
	        this.patientRepository = patientRepository;
	        this.doctorRepository=doctorRepository;
	        this.receptionistRepository=receptionistRepository;
	    }

	 
	    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
	        Optional<Patient> optionalPatient = patientRepository.getById(patientId);
	        if (optionalPatient.isPresent()) {
	            Patient patient = optionalPatient.get();
	            return appointmentRepository.getByPatientId(patientId);
	        } else {
	            throw new NotFoundException("Patient not found");
	        }
	    }

	    
	    public Appointment createAppointment(Appointment appointment) {
	  
	    	int doctorId=appointment.getDoctor().getId();
	         Doctor optionalDoctor=doctorRepository.getById(doctorId);
	    	if(optionalDoctor!=null) {
	    		
	    		appointment.setDoctor(optionalDoctor);
	    	}
	    	else {
	    		 throw new NotFoundException("Doctor not found with id:"+doctorId);
	    	}
	     
	       Long patientId = appointment.getPatient().getId();
	       
	        Optional<Patient> optionalPatient = patientRepository.getById(patientId);
	        if (optionalPatient.isPresent()) {
	            Patient patient = optionalPatient.get();
	            appointment.setPatient(patient);
	            
	        } else {
	            throw new NotFoundException("Patient not found with id:"+patientId);
	        }
	        
	        return appointmentRepository.saveAppointment(appointment);
	        
	        
	    }
	    
	  
	    public List<LocalTime> getBookedTimeSlots(BookedTimeSlotsRequest req){
	    	Doctor doctor=req.getDoctor();
	    	List<LocalTime> bookedTimeSlotsList=new ArrayList<LocalTime>();
	    	String appointmentDate=req.getAppointmentDate();
	    	List<Appointment> appointments=appointmentRepository.getByDoctorAndDate(doctor,appointmentDate);
	    	 for(Appointment eAppointment:appointments) {
	        	 LocalTime t=eAppointment.getTime();
	        	bookedTimeSlotsList.add(t);
	         }
	  
	    	return bookedTimeSlotsList;
	    }
	  
	  
	    public ResponseEntity<Appointment> updateAppointment(Long id, Appointment updatedAppointment) {
	        Optional<Appointment> optionalAppointment = appointmentRepository.getById(id);
	        if (optionalAppointment.isPresent()) {
	            Appointment appointment = optionalAppointment.get();
	            appointment.setDate(updatedAppointment.getDate());
	            appointment.setTime(updatedAppointment.getTime());
	            appointment.setProblem(updatedAppointment.getProblem());
	            //appointment.setPrescription(updatedAppointment.getPrescription());
	            appointment.setIncomingDate(updatedAppointment.getIncomingDate());
	            appointment.setIncomingTime(updatedAppointment.getIncomingTime());
	            appointment.setOutgoingDate(updatedAppointment.getOutgoingDate());
	            appointment.setOutgoingTime(updatedAppointment.getOutgoingTime());
	        	int doctorId=updatedAppointment.getDoctor().getId();
	        	Doctor optionalDoctor=doctorRepository.getById(doctorId);
	        	if(optionalDoctor!=null) {
	        		appointment.setDoctor(optionalDoctor);
	        	}
	        	else {
	        		 throw new NotFoundException("Doctor not found with id:"+doctorId);
	        	}
	         
	            
	            Long patientId = updatedAppointment.getPatient().getId();
	            Optional<Patient> optionalPatient = patientRepository.getById(patientId);
	            if (optionalPatient.isPresent()) {
	                Patient patient = optionalPatient.get();
	                appointment.setPatient(patient);
	                appointmentRepository.saveAppointment(appointment);
	                return ResponseEntity.ok(appointment);
	            } else {
	                throw new NotFoundException("Patient not found with id:"+patientId);
	            }
	            
	         
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    
	    public String deleteAppointment(Long id) {
	        Optional<Appointment> optionalAppointment = appointmentRepository.getById(id);
	        if (optionalAppointment.isPresent()) {
	            appointmentRepository.delete(id);
	            return "Appointment deleted successfully";
	        } else {
	            return "Appointment not found";
	        }
	    }


		public List<Appointment> getAllAppointments() {
			// TODO Auto-generated method stub
			return appointmentRepository.getAll();
		}


		public List<Appointment> getAppointmentsByDate(String date) {
			// TODO Auto-generated method stub
			return appointmentRepository.getByDate(date);
		}
}
