package com.att.attcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.att.attcare.controller.NotFoundException;
import com.att.attcare.dao.UserRepository;
import com.att.attcare.model.User;



@Service
public class AdminService {

	 private final UserRepository userRepository;

	    public AdminService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    public List<User> getAllAdmins() {
	        return userRepository.findAllByRole("ADMIN");
	    }

	   
	
	    
	    public Optional<User> getAdminByEmail(String email) {
	        Optional<User> optionalAdmin = userRepository.findByEmail(email);
	        return optionalAdmin;
	    }

	   
	    public ResponseEntity<User> updateAdmin(int id,  User updatedAdmin) {
	        Optional<User> optionalAdmin = userRepository.findById(id);
	        if (optionalAdmin.isPresent()) {
	        	updatedAdmin.setId(id);
				/*
				 * Doctor doctor = optionalDoctor.get();
				 * doctor.setName(updatedDoctor.getName());
				 * doctor.setEmail(updatedDoctor.getEmail());
				 * doctor.setPassword(updatedDoctor.getPassword());
				 * doctor.setSpecialization(updatedDoctor.getSpecialization());
				 * doctor.setGender(updatedDoctor.getGender());
				 * doctor.setMobile(updatedDoctor.getMobile());
				 * doctor.setExperience(updatedDoctor.getExperience());
				 */
	           userRepository.save(updatedAdmin);
	            return ResponseEntity.ok(updatedAdmin);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	   
	   
		
		  public void deleteAdmin( Long id) throws NotFoundException{ Optional<User>
		  optionalAdmin = userRepository.findById(id); 
		  if(optionalAdmin.isPresent()) {
			  userRepository.deleteById(id);
		  
		  } else { 
			  throw new NotFoundException("Admin not found with id:"+id); } }
		 
	    
}
