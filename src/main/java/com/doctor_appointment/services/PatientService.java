package com.doctor_appointment.services;

import com.doctor_appointment.dto.CouponDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {

	List<CouponDTO> getCouponsByPatientId(int patientId);

	List<CouponDTO> getByUuidPatient(UUID uuidPatient);
}
