package com.att.attcare.config;

import jakarta.servlet.Filter;
import jakarta.websocket.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.att.attcare.controller.DoctorController;

import static com.att.attcare.model.Permission.ADMIN_CREATE;
import static com.att.attcare.model.Permission.ADMIN_DELETE;
import static com.att.attcare.model.Permission.ADMIN_READ;
import static com.att.attcare.model.Permission.ADMIN_UPDATE;
import static com.att.attcare.model.Permission.DOCTOR_READ;
import static com.att.attcare.model.Permission.DOCTOR_UPDATE;
import static com.att.attcare.model.Permission.RECEPTIONIST_CREATE;
import static com.att.attcare.model.Permission.RECEPTIONIST_DELETE;
import static com.att.attcare.model.Permission.RECEPTIONIST_READ;
import static com.att.attcare.model.Permission.RECEPTIONIST_UPDATE;
import static com.att.attcare.model.Role.ADMIN;
import static com.att.attcare.model.Role.DOCTOR;
import static com.att.attcare.model.Role.RECEPTIONIST;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import java.security.spec.EncodedKeySpec;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  

  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers(
                "/auth/**"
        )
          .permitAll()
		  	  
			
			  .requestMatchers("/attcare/doctors/**").hasAnyRole(ADMIN.name())
			  .requestMatchers(GET, "/attcare/doctors/**").hasAnyAuthority(ADMIN_READ.name())
			  .requestMatchers(POST,"/attcare/doctors/**").hasAnyAuthority(ADMIN_CREATE.name())
			  .requestMatchers(PUT, "/attcare/doctors/**").hasAnyAuthority(ADMIN_UPDATE.name())
			  .requestMatchers(DELETE, "/attcare/doctors/**").hasAnyAuthority(ADMIN_DELETE.name())
			 
			  
			  
			  .requestMatchers("/attcare/receptionists/**").hasAnyRole(ADMIN.name())
				 
			  .requestMatchers(GET, "/attcare/receptionists/**").hasAnyAuthority(ADMIN_READ.name())
			  .requestMatchers(POST,"/attcare/receptionists/**").hasAnyAuthority(ADMIN_CREATE.name())
			  .requestMatchers(PUT, "/attcare/receptionists/**").hasAnyAuthority(ADMIN_UPDATE.name())
			  .requestMatchers(DELETE, "/attcare/receptionists/**").hasAnyAuthority(ADMIN_DELETE.name())
			  
			  
			  .requestMatchers("/attcare/patients/**").hasAnyRole(ADMIN.name(), RECEPTIONIST.name())
				 
				
			  .requestMatchers(GET, "/attcare/patients/**").hasAnyAuthority(ADMIN_READ.name(), RECEPTIONIST_READ.name())
			  .requestMatchers(POST,"/attcare/patients/**").hasAnyAuthority( ADMIN_CREATE.name(), RECEPTIONIST_CREATE.name())
			  .requestMatchers(PUT, "/attcare/patients/**").hasAnyAuthority( ADMIN_UPDATE.name(),RECEPTIONIST_UPDATE.name())
			  .requestMatchers(DELETE, "/attcare/patients/**").hasAnyAuthority(ADMIN_DELETE.name() ,RECEPTIONIST_DELETE.name())
			 
			  .requestMatchers("/attcare/appointments/**").hasAnyRole(ADMIN.name(),RECEPTIONIST.name())
				 
				
			  .requestMatchers(GET, "/attcare/appointments/**").hasAnyAuthority(ADMIN_READ.name(),RECEPTIONIST_READ.name())
			  .requestMatchers(POST,"/attcare/appointments/**").hasAnyAuthority(RECEPTIONIST_CREATE.name())
			  .requestMatchers(PUT, "/attcare/appointments/**").hasAnyAuthority(RECEPTIONIST_UPDATE.name())
			  .requestMatchers(DELETE, "/attcare/appointments/**").hasAnyAuthority(RECEPTIONIST_DELETE.name())
			 
			 
       /* .requestMatchers("/admin/**").hasRole(ADMIN.name())

        .requestMatchers(GET, "/admin/**").hasAuthority(ADMIN_READ.name())
        .requestMatchers(POST, "/admin/**").hasAuthority(ADMIN_CREATE.name())
        .requestMatchers(PUT, "/admin/**").hasAuthority(ADMIN_UPDATE.name())
        .requestMatchers(DELETE, "/admin/**").hasAuthority(ADMIN_DELETE.name())*/


        .anyRequest()
          .authenticated()
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
}
