package com.doctor_appointment.services.impl;

import com.doctor_appointment.dto.CouponDTO;
import com.doctor_appointment.entities.Coupon;
import com.doctor_appointment.entities.Patient;
import com.doctor_appointment.exceptions.coupon.CouponErrorType;
import com.doctor_appointment.exceptions.coupon.CouponException;
import com.doctor_appointment.exceptions.patient.PatientErrorType;
import com.doctor_appointment.mappers.CouponMapper;
import com.doctor_appointment.repositories.CouponRepository;
import com.doctor_appointment.repositories.PatientRepository;
import com.doctor_appointment.services.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CouponServiceImpl implements CouponService {

	private final CouponRepository couponRepository;
	private final PatientRepository patientRepository;
	private final CouponMapper couponMapper;

	@Autowired
	public CouponServiceImpl(CouponRepository couponRepository, PatientRepository patientRepository, CouponMapper couponMapper) {
		this.couponRepository = couponRepository;
		this.patientRepository = patientRepository;
		this.couponMapper = couponMapper;
	}

	@Override
	public CouponDTO occupyCouponByCouponId(int couponId, int patientId) {

		Optional<Patient> patient = patientRepository.getById(patientId);
		if (patient.isEmpty()) {
			log.warn("IN method getCouponsByPatientId - patient by id: {} "
					+ "not found", patientId);
			throw new CouponException(String.format(PatientErrorType.PATIENT_BY_ID_NOT_FOUND
					.getDescription(), patientId));
		}

		Optional<Coupon> freeCoupon = couponRepository.getById(couponId).filter(c -> c.getPatient() == null);
		if (freeCoupon.isEmpty()) {
			log.warn("IN method occupyCouponByCouponId - coupon by id: {} "
					+ "not found", couponId);
			throw new CouponException(String.format(CouponErrorType.COUPON_BY_ID_NOT_FOUND
					.getDescription(), couponId));
		} else {
			freeCoupon.get().setPatient(patient.get());
			CouponDTO occupyCouponDTO = couponMapper.toCouponDTO(couponRepository.save(freeCoupon.get()));
			log.info("IN method occupyCouponByCouponId - coupon by id {} was occupy", occupyCouponDTO.getId());
			return occupyCouponDTO;
		}
	}

	@Override
	public List<CouponDTO> getCouponsByDoctor_IdAndAppointmentTime(int doctorId, Timestamp appointmentTime) {

		List<Coupon> coupons = couponRepository.getCouponsByDoctor_IdAndAppointmentTime(doctorId, appointmentTime)
				.stream().filter(c -> c.getPatient() == null).toList();

		if (coupons.isEmpty()) {
			log.warn("IN method getFreeCouponsToTheDoctorByDate - coupons by doctor id: {} and date of appointment: {} "
					+ "not found", doctorId, appointmentTime);
			throw new CouponException(String.format(CouponErrorType.COUPONS_NOT_FOUND
					.getDescription(), doctorId, appointmentTime));
		} else {
			List<CouponDTO> freeCoupons = new ArrayList<>(couponMapper.toCouponDTOs(coupons));
			log.info("IN method getFreeCouponsToTheDoctorByDate - {} coupons found", freeCoupons.size());
			return freeCoupons;
		}
	}
}