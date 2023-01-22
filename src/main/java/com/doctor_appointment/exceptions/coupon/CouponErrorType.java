package com.doctor_appointment.exceptions.coupon;

public enum CouponErrorType {

	COUPON_BY_ID_NOT_FOUND("Coupon not found by id: %s"),
	COUPON_BY_UUID_NOT_FOUND("Coupon not found by UUID id: %s"),
	COUPONS_NOT_FOUND("Coupons not found");

	private final String description;

	CouponErrorType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
