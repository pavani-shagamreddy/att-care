package com.att.attcare.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

	@NotBlank(message = "username shouldn't be empty")
    private String name;
    private int age;
    
    @Email(message ="invalid email address ")
    private String email;
    
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile no")
	private String mobile;
	private String gender;
    

  
  
    // Constructors, getters, and setters
}
