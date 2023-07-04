package com.att.attcare.dto;



import com.att.attcare.model.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookedTimeSlotsRequest {

	 private Doctor doctor;
	 private String appointmentDate;
}
