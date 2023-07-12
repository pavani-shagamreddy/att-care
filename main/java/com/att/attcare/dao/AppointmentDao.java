package com.att.attcare.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.att.attcare.model.Appointment;
import com.att.attcare.model.Doctor;
import com.att.attcare.repository.AppointmentRepository;
import com.att.attcare.repository.DoctorRepository;
import com.att.attcare.repository.PatientRepository;
import com.att.attcare.repository.ReceptionistRepository;

@Component
public class AppointmentDao {
	  private final AppointmentRepository appointmentRepository;
	    private final PatientRepository patientRepository;
	    private final DoctorRepository doctorRepository;
	    private final ReceptionistRepository receptionistRepository;

	    public AppointmentDao(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository,  ReceptionistRepository receptionistRepository) {
	        this.appointmentRepository = appointmentRepository;
	        this.patientRepository = patientRepository;
	        this.doctorRepository=doctorRepository;
	        this.receptionistRepository=receptionistRepository;
	    }

		public List<Appointment> getByPatientId(Long patientId) {
			// TODO Auto-generated method stub
			return appointmentRepository.findByPatientId(patientId);
		}

		public Appointment saveAppointment(Appointment appointment) {
			// TODO Auto-generated method stub
			return appointmentRepository.save(appointment);
		}

		public List<Appointment> getByDoctorAndDate(Doctor doctor, String appointmentDate) {
			// TODO Auto-generated method stub
			return appointmentRepository.findByDoctorAndDate(doctor,appointmentDate);
		}

		public Optional<Appointment> getById(Long id) {
			// TODO Auto-generated method stub
			return appointmentRepository.findById(id);
		}

		public void delete(Long id) {
			// TODO Auto-generated method stub
			 appointmentRepository.deleteById(id);
		}

		public List<Appointment> getAll() {
			// TODO Auto-generated method stub
			return appointmentRepository.findAll();
		}

		public List<Appointment> getByDate(String date) {
			// TODO Auto-generated method stub
			return appointmentRepository.findByDate(date);
		}


}
