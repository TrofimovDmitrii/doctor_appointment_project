package com.doctor_appointment.configurations;

import com.doctor_appointment.repositories.CouponRepository;
import com.doctor_appointment.repositories.DoctorRepository;
import com.doctor_appointment.services.impl.ScheduleWebServiceImpl;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WSConfig {

	private final CouponRepository couponRepository;
	private final DoctorRepository doctorRepository;
	private final Bus bus;

	@Autowired
	public WSConfig(CouponRepository couponRepository, DoctorRepository doctorRepository, Bus bus) {
		this.couponRepository = couponRepository;
		this.doctorRepository = doctorRepository;
		this.bus = bus;
	}

	@Bean
	public Endpoint scheduleEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new ScheduleWebServiceImpl(couponRepository, doctorRepository));
		endpoint.publish("/scheduleService");

		return endpoint;
	}
}
