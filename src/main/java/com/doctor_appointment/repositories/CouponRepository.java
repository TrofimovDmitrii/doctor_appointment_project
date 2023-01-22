package com.doctor_appointment.repositories;

import com.doctor_appointment.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Optional<Coupon> getById(int couponId);

	List<Coupon> getCouponsByDoctor_IdAndAppointmentTime(int doctorId, Timestamp appointmentTime);
}
