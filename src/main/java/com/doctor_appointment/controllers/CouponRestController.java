package com.doctor_appointment.controllers;

import com.doctor_appointment.dto.CouponDTO;
import com.doctor_appointment.exceptions.coupon.CouponCustomExceptionHandler;
import com.doctor_appointment.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CouponCustomExceptionHandler
@RestController
@RequestMapping("/api/coupons")
public class CouponRestController {

	private final CouponService couponService;

	@Autowired
	public CouponRestController(CouponService couponService) {
		this.couponService = couponService;
	}

	@PutMapping("/occupy/{couponId}/{patientId}")
	public ResponseEntity<CouponDTO> occupyCouponByCouponId(@PathVariable int couponId,
															@PathVariable int patientId) {
		return new ResponseEntity<>(couponService.occupyCouponByCouponId(couponId, patientId), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}/{date}")
	public ResponseEntity<List<CouponDTO>> getCouponsByDoctor_IdAndAppointmentTime(
			@PathVariable(name = "id") int doctorId,
			@PathVariable(name = "date") Timestamp appointmentTime) {
		return new ResponseEntity<>(couponService.getCouponsByDoctor_IdAndAppointmentTime(doctorId, appointmentTime), HttpStatus.OK);
	}
}
