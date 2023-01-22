package com.doctor_appointment.mappers;

import com.doctor_appointment.dto.CouponDTO;
import com.doctor_appointment.entities.Coupon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CouponMapper {

	CouponDTO toCouponDTO(Coupon coupon);

	List<CouponDTO> toCouponDTOs (List<Coupon> coupons);
}
