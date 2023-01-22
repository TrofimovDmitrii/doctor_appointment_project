package com.doctor_appointment.dto;

import com.doctor_appointment.entities.Specialization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DoctorDTO {

	private int id;
	private UUID uuidDoctor;
	private String firstName;
	private String lastName;
	private String patronymic;
	private Specialization specialization;
}
