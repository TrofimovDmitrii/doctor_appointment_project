package com.doctor_appointment.services.impl;

import com.doctor_appointment.dto.CouponDTO;
import com.doctor_appointment.entities.Coupon;
import com.doctor_appointment.entities.Patient;
import com.doctor_appointment.exceptions.coupon.CouponErrorType;
import com.doctor_appointment.exceptions.coupon.CouponException;
import com.doctor_appointment.exceptions.patient.PatientErrorType;
import com.doctor_appointment.mappers.CouponMapper;
import com.doctor_appointment.mappers.PatientMapper;
import com.doctor_appointment.repositories.CouponRepository;
import com.doctor_appointment.repositories.PatientRepository;
import com.doctor_appointment.services.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	private final CouponMapper couponMapper;

	@Autowired
	public PatientServiceImpl(PatientRepository patientRepository, CouponRepository couponRepository, PatientMapper patientMapper, CouponMapper couponMapper) {
		this.patientRepository = patientRepository;
		this.couponMapper = couponMapper;
	}

	@Override
	public List<CouponDTO> getCouponsByPatientId(int patientId) {

		Optional<Patient> patient = patientRepository.findById(patientId);
		if (patient.isEmpty()) {
			log.warn("IN method getCouponsByPatientId - patient by id: {} "
					+ "not found", patientId);
			throw new CouponException(String.format(PatientErrorType.PATIENT_BY_ID_NOT_FOUND
					.getDescription(), patientId));
		}

		List<Coupon> coupons = new ArrayList<>(patient.get().getPatientCoupons());
		if (coupons.isEmpty()) {
			log.warn("IN method getCouponsByPatientId - coupons by patient id: {} "
					+ "not found", patientId);
			throw new CouponException(String.format(CouponErrorType.COUPON_BY_ID_NOT_FOUND
					.getDescription(), patientId));
		} else {
			List<CouponDTO> couponDTOS = new ArrayList<>(couponMapper.toCouponDTOs(coupons));
			log.info("IN method getCouponsByPatientId - {} coupons found", couponDTOS.size());
			return couponDTOS;
		}
	}

	@Override
	public List<CouponDTO> getByUuidPatient(UUID uuidPatient) {

		Optional<Patient> patient = patientRepository.getByUuidPatient(uuidPatient);
		if (patient.isEmpty()) {
			log.warn("IN method getByUuidPatient - patient by UUID: {} "
					+ "not found", uuidPatient);
			throw new CouponException(String.format(PatientErrorType.PATIENT_BY_UUID_NOT_FOUND
					.getDescription(), uuidPatient));
		}

		List<Coupon> coupons = new ArrayList<>(patient.get().getPatientCoupons());
		if (coupons.isEmpty()) {
			log.warn("IN method getByUuidPatient - coupons by patient UUID: {} "
					+ "not found", uuidPatient);
			throw new CouponException(String.format(CouponErrorType.COUPON_BY_UUID_NOT_FOUND
					.getDescription(), uuidPatient));
		} else {

			List<CouponDTO> couponDTOS = new ArrayList<>(couponMapper.toCouponDTOs(coupons));
			log.info("IN method getByUuidPatient - {} coupons found", couponDTOS.size());
			return couponDTOS;
		}
	}
}
