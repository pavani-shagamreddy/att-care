package com.att.attcare.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.att.attcare.model.Receptionist;
import com.att.attcare.model.User;
import com.att.attcare.service.AdminService;
import com.att.attcare.service.DoctorService;
import com.att.attcare.service.ReceptionistService;

@RestController
@RequestMapping("/attcare/admin")
@PreAuthorize("hasRole('ADMIN')")

public class AdminController {
	private final AuthenticationService service;	
    private final DoctorService doctorService;
    private final ReceptionistService receptionistService;
    private final AdminService adminService;
    public AdminController(AuthenticationService service,DoctorService doctorService,ReceptionistService receptionistService,AdminService adminService) {
    	this.service=service;
    	this.doctorService=doctorService;
    	this.receptionistService=receptionistService;
    	this.adminService=adminService;
    }
    
	  @PostMapping("/register")
	  public String register(
	      @RequestBody @Valid RegisterRequest request
	  ) {
		  String email=request.getEmail();
		  Optional<User> optionalUser=service.getUserByEmail(email);
		  if(optionalUser.isPresent()) {
			  return "Admin Already exists!";
		  }
		  else {
			  service.register(request);
			  return "Admin registered Successfully";
		  }
	  }
	  
	  @PostMapping("/register-doctor")
	  public String doctorRegister(
	      @RequestBody @Valid RegisterRequest request
	  ) {
		  String email=request.getEmail();
		  String password=request.getPassword();
		  Optional<Doctor> optionalDoctor=doctorService.getDoctorByEmail(email);
		  if(optionalDoctor.isPresent()){
			  return "Doctor Already registered!";
		  }
		  else {

			  Doctor doctor=new Doctor();
			  doctor.setEmail(email);
			  doctor.setName(request.getName());
			  doctor.setMobile(request.getMobile());
			  doctor.setGender(request.getGender());
			  doctor.setSpecialization(request.getSpecialization());
			  doctor.setExperience(request.getExperience());
			  doctorService.createDoctor(doctor);
			  service.register(request);
			  
			  return "Doctor registered successfully";
		  }
		 
	  }
	  
	
	 @PostMapping("/register-receptionist")
	  public String registerReceptionist(
	      @RequestBody @Valid RegisterRequest request
	  ) {
		 String email=request.getEmail();
		  String password=request.getPassword();
		  Optional<Receptionist> optionalReceptionist=receptionistService.getReceptionistByEmail(email);
		  if(optionalReceptionist.isPresent()) {
			  return "Receptionist already registered!";
		  }
		  else {
			  Receptionist receptionist=new Receptionist();
			  receptionist.setEmail(email);
			  receptionist.setPassword(password);
			  receptionist.setMobile(request.getMobile());
			  receptionist.setName(request.getName());
			  receptionistService.createReceptionist(receptionist);
			  service.register(request);
			  return "Receptionist registered successfully";
		  }
	
	  }

		/*
		 * @GetMapping
		 * 
		 * @PreAuthorize("hasAuthority('admin:read')") public String get() { return
		 * "GET:: admin controller"; }
		 * 
		 * @PostMapping
		 * 
		 * @PreAuthorize("hasAuthority('admin:create')")
		 * 
		 * @Hidden public String post() { return "POST:: admin controller"; }
		 * 
		 * @PutMapping
		 * 
		 * @PreAuthorize("hasAuthority('admin:update')")
		 * 
		 * @Hidden public String put() { return "PUT:: admin controller"; }
		 * 
		 * @DeleteMapping
		 * 
		 * @PreAuthorize("hasAuthority('admin:delete')")
		 * 
		 * @Hidden public String delete() { return "DELETE:: admin controller"; }
		 */
	 
	 @GetMapping
	    public List<User> getAllAdmins() {
	        return adminService.getAllAdmins();
	    }

	   @GetMapping("/{email}")
	    public Optional<User> getAdminByEmail(@PathVariable String email) {
	       
	        return adminService.getAdminByEmail(email);
	    }

	   
	   @Transactional
	   @DeleteMapping("/{id}")
	    public void deleteDoctor(@PathVariable Long id) throws NotFoundException{
	      adminService.deleteAdmin(id);
	   }
}
