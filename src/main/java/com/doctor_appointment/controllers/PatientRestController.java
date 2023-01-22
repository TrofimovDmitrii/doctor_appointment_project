package com.doctor_appointment.controllers;

import com.doctor_appointment.dto.CouponDTO;
import com.doctor_appointment.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
public class PatientRestController {

	private final PatientService patientService;

	public PatientRestController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<List<CouponDTO>> getCouponsByPatientId(@PathVariable(name = "id") int patientId) {
		return new ResponseEntity<>(patientService.getCouponsByPatientId(patientId), HttpStatus.OK);
	}

	@GetMapping(value = "/uuid/{uuid}")
	public ResponseEntity<List<CouponDTO>> getByUuidPatient(@PathVariable(name = "uuid") UUID uuid) {
		return new ResponseEntity<>(patientService.getByUuidPatient(uuid), HttpStatus.OK);
	}
}
