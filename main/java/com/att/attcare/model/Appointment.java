package com.att.attcare.model;


import java.time.LocalTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment Id")
    private Long id;
    

   
    @Column(name = "Appointment Date")
    private String date;

    @Column(name = "Appointment Time")
    private LocalTime time;
    
    private String problem;
    
    private String prescription;
    
   
   
  

    private String incomingDate;
    private LocalTime incomingTime;


    private String outgoingDate;
    private LocalTime outgoingTime;
    
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "receptionist id") private Receptionist receptionist;
	 */


    
    @ManyToOne
    @JoinColumn(name = "doctor id")
    private Doctor doctor;

  
    @ManyToOne
    @JoinColumn(name = "patient id")
    private Patient patient;

    // Constructors, getters, and setters
}
