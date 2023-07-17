package com.att.attcare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.attcare.auth.AuthenticationResponse;
import com.att.attcare.auth.AuthenticationService;
import com.att.attcare.auth.RegisterRequest;
import com.att.attcare.model.Doctor;
import com.att.attcare.service.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attcare/doctors")
@Tag(name = "Doctor")
@RequiredArgsConstructor
public class DoctorController {
	
	@Autowired
	private final DoctorService doctorService;

	
	  @Operation( description = "Get endpoint for doctor", summary =
	  "This is a summary for doctor get endpoint", responses = {
	  
	  @ApiResponse( description = "Success", responseCode = "200" ),
	  
	  @ApiResponse( description = "Unauthorized / Invalid Token", responseCode =
	  "403" ) }
	  
	  )
	 
	 	@GetMapping
	    public List<Doctor> getAllDoctors() {
	        return doctorService.getAllDoctors();
	    }

	   @GetMapping("/{id}")
	    public Doctor getDoctorById(@PathVariable  int id) throws NotFoundException {
	       
	        return doctorService.getDoctorById(id);
	    }

		/*
		 * @PostMapping public String createDoctor(@RequestBody Doctor doctor) { //
		 * Perform any necessary validation or business logic
		 * 
		 * doctorService.createDoctor(doctor);
		 * 
		 * return "Doctor registered successfully"; }
		 */

		/*
		 * @PutMapping("/{id}") public ResponseEntity<Doctor> updateDoctor(@PathVariable
		 * Long id, @RequestBody Doctor updatedDoctor) { return
		 * doctorService.updateDoctor(id, updatedDoctor); }
		 */
	   
	   
	   
	   @PutMapping("/{id}")
	    public ResponseEntity<Doctor> updateDoctorByEmail(@PathVariable int id, @RequestBody Doctor updatedDoctor) {
	       return doctorService.updateDoctorById(id, updatedDoctor);
	    }
	   
	   @Transactional
	   @DeleteMapping("/{id}")
	    public String deleteDoctor(@PathVariable int id) throws NotFoundException{
	      doctorService.deleteDoctor(id);
	      return "Doctor removed successfully";
	   }

}
