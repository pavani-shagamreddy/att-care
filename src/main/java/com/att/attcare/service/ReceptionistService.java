package com.att.attcare.service;

import java.util.List;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.att.attcare.auth.AuthenticationRequest;
import com.att.attcare.auth.AuthenticationResponse;
import com.att.attcare.dao.ReceptionistDao;
import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;
import com.att.attcare.model.Receptionist;
import com.att.attcare.repository.DoctorRepository;
import com.att.attcare.repository.ReceptionistRepository;

@Service
public class ReceptionistService {
    private final ReceptionistDao repository;

    public ReceptionistService(ReceptionistDao repository) {
        this.repository = repository;
    }

    public List<Receptionist> getAllReceptionists() {
        return repository.getAll();
    }

   
    public ResponseEntity<Receptionist> getReceptionistById(Long id) {
        Optional<Receptionist> optionalReceptionist = repository.getById(id);
        return  optionalReceptionist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Receptionist createReceptionist(Receptionist receptionist) {
        // Perform any necessary validation or business logic
    	Receptionist r=repository.saveReceptionist(receptionist);
        return r;
    }

   
    public ResponseEntity<Receptionist> updateReceptionist(Long id,  Receptionist updatedReceptionist) {
        Optional<Receptionist>  optionalReceptionist = repository.getById(id);
        if ( optionalReceptionist.isPresent()) {
            Receptionist receptionist = optionalReceptionist.get();
            receptionist.setName(updatedReceptionist.getName());
            receptionist.setEmail(updatedReceptionist.getEmail());
            receptionist.setPassword(updatedReceptionist.getPassword());
  
            receptionist.setMobile(updatedReceptionist.getMobile());
           repository.saveReceptionist(updatedReceptionist);
            return ResponseEntity.ok(receptionist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    public ResponseEntity<Receptionist> updateReceptionistByEmail(String email,  Receptionist updatedReceptionist) {
        Optional<Receptionist>  optionalReceptionist = repository.getByEmail(email);
        if ( optionalReceptionist.isPresent()) {
            Receptionist receptionist = optionalReceptionist.get();
            receptionist.setName(updatedReceptionist.getName());
            receptionist.setEmail(updatedReceptionist.getEmail());
            receptionist.setPassword(updatedReceptionist.getPassword());
  
            receptionist.setMobile(updatedReceptionist.getMobile());
           repository.saveReceptionist(updatedReceptionist);
            return ResponseEntity.ok(receptionist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
 
    public String deleteReceptionist( Long id) {
        Optional<Receptionist> optionalReceptionist = repository.getById(id);
        if (optionalReceptionist.isPresent()) {
            repository.delete(id);
            return "Receptionist removed successfully";
        } else {
            return "Receptionist not found";
        }
    }

	public Optional<Receptionist> getReceptionistByEmail(String email) {
		Optional<Receptionist> optionalReceptionist=repository.getByEmail(email);
		return optionalReceptionist;
	}
    
    
    // Add more service methods as needed
}
