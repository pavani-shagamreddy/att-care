package com.att.attcare.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.attcare.dao.ReceptionistRepository;
import com.att.attcare.model.Doctor;
import com.att.attcare.model.Patient;
import com.att.attcare.model.Receptionist;
import com.att.attcare.service.ReceptionistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attcare/receptionists")
@Tag(name = "Receptionist")
@RequiredArgsConstructor
public class ReceptionistController {
	private final ReceptionistService service;
	private final ReceptionistRepository receptionistRepository;
	 @Operation(
	            description = "Get endpoint for Receptionist",
	            summary = "This is a summary for Receptionist get endpoint",
	            responses = {
	                    @ApiResponse(
	                            description = "Success",
	                            responseCode = "200"
	                    ),
	                    @ApiResponse(
	                            description = "Unauthorized / Invalid Token",
	                            responseCode = "403"
	                    )
	            }

	    )
	
	 @GetMapping
	    public List<Receptionist> getAllReceptionists() {
	        return service.getAllReceptionists();
	    }

	   @GetMapping("/{id}")
	    public ResponseEntity<Receptionist> getReceptionistById(@PathVariable  Long id) {
	       
	        return service.getReceptionistById(id);
	    }

	   @PostMapping
	   public Receptionist createReceptionist(@RequestBody Receptionist receptionist) {
	        // Perform any necessary validation or business logic

	      return  service.createReceptionist(receptionist);
	        
	     
	    }

		/*
		 * @PutMapping("/{id}") public ResponseEntity<Doctor> updateDoctor(@PathVariable
		 * Long id, @RequestBody Doctor updatedDoctor) { return
		 * doctorService.updateDoctor(id, updatedDoctor); }
		 */
	   
	   
	   
	   @PutMapping("/{email}")
	    public ResponseEntity<Receptionist> updateReceptionistByEmail(@PathVariable String email, @RequestBody Receptionist updatedReceptionist) {
	       return service.updateReceptionistByEmail(email, updatedReceptionist);
	    }
	   
		/*
		 * @DeleteMapping("/{id}") public ResponseEntity<Void>
		 * deleteReceptionist(@PathVariable Long id) { return
		 * service.deleteReceptionist(id); }
		 */
	   @Transactional
	   @DeleteMapping("/{id}")
	    public String deleteReceptionist(@PathVariable Long id) {
	      return service.deleteReceptionist(id);
	    }
}
