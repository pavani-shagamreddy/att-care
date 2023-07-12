package com.att.attcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.att.attcare.controller.NotFoundException;
import com.att.attcare.dao.AdminDao;
import com.att.attcare.model.User;
import com.att.attcare.repository.UserRepository;



@Service
public class AdminService {

	 private final AdminDao userRepository;

	    public AdminService(AdminDao userRepository) {
	        this.userRepository = userRepository;
	    }

	    public List<User> getAllAdmins() {
	        return userRepository.getAllByRole("ADMIN");
	    }

	   
	
	    
	    public Optional<User> getAdminByEmail(String email) {
	        Optional<User> optionalAdmin = userRepository.getByEmail(email);
	        return optionalAdmin;
	    }

	   
	    public ResponseEntity<User> updateAdmin(int id,  User updatedAdmin) {
	        Optional<User> optionalAdmin = userRepository.getById(id);
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
	           userRepository.saveuser(updatedAdmin);
	            return ResponseEntity.ok(updatedAdmin);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	   
	   
		
		  public void deleteAdmin( int id) throws NotFoundException{ 
			  Optional<User> optionalAdmin = userRepository.getById(id); 
		  if(optionalAdmin.isPresent()) {
			  userRepository.delete(id);
		  
		  } else { 
			  throw new NotFoundException("Admin not found with id:"+id); } }
		 
	    
}
