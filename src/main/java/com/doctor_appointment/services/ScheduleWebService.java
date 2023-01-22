package com.doctor_appointment.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://service.ws.schedule/", name = "ScheduleWebService")
public interface ScheduleWebService {

	@WebResult(name = "createdCoupons", targetNamespace = "")
	@RequestWrapper(
			localName = "createDoctorSchedule",
			targetNamespace = "http://service.ws.schedule/",
			className = "schedule.ws.services.ScheduleWebRequest")
	@WebMethod(action = "urn:createDoctorSchedule")
	@ResponseWrapper(
			localName = "createDoctorScheduleResponse",
			targetNamespace = "http://service.ws.schedule/",
			className = "schedule.ws.services.ScheduleWebResponse")
	String createDoctorSchedule(
			@WebParam(name = "doctorId", targetNamespace = "") int doctorId,
			@WebParam(name = "scheduleDate", targetNamespace = "") String scheduleDate,
			@WebParam(name = "receptionDuration", targetNamespace = "") long receptionDuration,
			@WebParam(name = "couponsNumber", targetNamespace = "") int couponsNumber);
}
