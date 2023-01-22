package com.doctor_appointment.services;

import com.doctor_appointment.dto.CouponDTO;

import java.sql.Timestamp;
import java.util.List;

public interface CouponService {

	CouponDTO occupyCouponByCouponId(int couponId, int patientId);

	List<CouponDTO> getCouponsByDoctor_IdAndAppointmentTime(int doctorId, Timestamp appointmentTime);
}
