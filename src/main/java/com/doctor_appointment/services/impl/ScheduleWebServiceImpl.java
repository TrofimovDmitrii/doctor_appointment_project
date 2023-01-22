package com.doctor_appointment.services.impl;

import com.doctor_appointment.entities.Coupon;
import com.doctor_appointment.entities.Doctor;
import com.doctor_appointment.exceptions.doctor.DoctorErrorType;
import com.doctor_appointment.exceptions.doctor.DoctorException;
import com.doctor_appointment.repositories.CouponRepository;
import com.doctor_appointment.repositories.DoctorRepository;
import com.doctor_appointment.services.ScheduleWebService;
import jakarta.jws.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebService(
		serviceName = "ScheduleWebService",
		portName = "SchedulePort",
		targetNamespace = "http://service.ws.schedule/",
		endpointInterface = "com.doctor_appointment.services.ScheduleWebService")
@Slf4j
public class ScheduleWebServiceImpl implements ScheduleWebService {

	private final CouponRepository couponRepository;
	private final DoctorRepository doctorRepository;

	@Autowired
	public ScheduleWebServiceImpl(CouponRepository couponRepository, DoctorRepository doctorRepository) {
		this.couponRepository = couponRepository;
		this.doctorRepository = doctorRepository;
	}


	@Override
	public String createDoctorSchedule(int doctorId, String scheduleDate, long receptionDuration, int couponsNumber) {

		List<Coupon> coupons = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		long duration = 0L;

		for (int i = 0; i < couponsNumber; i++) {
			Coupon coupon = new Coupon();

			Optional<Doctor> doctor = doctorRepository.findById(doctorId);
			if (doctor.isEmpty()) {
				log.warn("IN method createDoctorSchedule - doctor by id: {} "
						+ "not found", doctorId);
				throw new DoctorException(String.format(DoctorErrorType.DOCTOR_BY_ID_NOT_FOUND
						.getDescription(), doctorId));
			}

			LocalDateTime dateTime = LocalDateTime.parse(scheduleDate, formatter);
			coupon.setAppointmentTime(Timestamp.valueOf(dateTime.plusMinutes(duration)));
			coupon.setDoctor(doctor.get());
			coupons.add(coupon);
			duration += receptionDuration;
		}

		couponRepository.saveAll(coupons);
		return "The creation of the schedule was successful.";
	}
}