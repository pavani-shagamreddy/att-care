package com.att.attcare.auth;

import com.att.attcare.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank(message = "username shouldn't be empty")
  private String name;
  
  @Email(message ="invalid email address ")
  private String email;
  
  @Pattern(regexp = "^\\d{10}$",message = "invalid mobile no")
  private String mobile;
  
  private String password;
  private Role role;
  private String gender;
  private String specialization;
  private int experience;

}
