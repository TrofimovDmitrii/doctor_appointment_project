package com.doctor_appointment.exceptions.patient;

public enum PatientErrorType {

	PATIENT_BY_ID_NOT_FOUND("Patient not found by id: %s"),
	PATIENT_BY_UUID_NOT_FOUND("Patient not found by uuid: %s");
	private final String description;

	PatientErrorType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
