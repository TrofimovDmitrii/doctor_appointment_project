package com.doctor_appointment.dto;

import com.doctor_appointment.entities.Doctor;
import com.doctor_appointment.entities.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CouponDTO {

	private int id;
	private Doctor doctor;

	@JsonIgnoreProperties("patientCoupons")
	private Patient patient;
	private Timestamp appointmentTime;
}
