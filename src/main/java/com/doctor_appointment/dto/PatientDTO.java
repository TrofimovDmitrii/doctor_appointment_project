package com.doctor_appointment.dto;

import com.doctor_appointment.entities.Coupon;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PatientDTO {

	private int id;
	private UUID uuidPatient;
	private String firstName;
	private String lastName;
	private String patronymic;
	private String gender;
	private Date birthday;

	@JsonIgnoreProperties("patient")
	List<Coupon> patientCoupons = new ArrayList<>();
}
