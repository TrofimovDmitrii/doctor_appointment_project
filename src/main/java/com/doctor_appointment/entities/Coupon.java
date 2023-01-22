package com.doctor_appointment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(optional = false)
	@ToString.Exclude
	private Doctor doctor;

	@ManyToOne
	@ToString.Exclude
	private Patient patient;

	@Column(name = "appointment_time")
	private Timestamp appointmentTime;
}
