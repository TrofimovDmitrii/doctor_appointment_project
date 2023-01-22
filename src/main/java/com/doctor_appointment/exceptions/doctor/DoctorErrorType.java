package com.doctor_appointment.exceptions.doctor;

public enum DoctorErrorType {

	DOCTOR_BY_ID_NOT_FOUND("Doctor not found by id: %s");
	private final String description;

	DoctorErrorType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
