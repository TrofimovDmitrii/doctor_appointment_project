package com.doctor_appointment.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank
	@Column(name = "uuid_patient")
	private UUID uuidPatient;

	@NotBlank
	@Size(max = 25)
	@Column(name = "first_name")
	private String firstName;

	@NotBlank
	@Size(max = 25)
	@Column(name = "last_name")
	private String lastName;

	@Size(max = 25)
	@Column(name = "patronymic")
	private String patronymic;

	@NotBlank
	@Size(max = 25)
	@Column(name = "gender")
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private Date birthday;

	@OneToMany(mappedBy = "patient")
	@ToString.Exclude
	List<Coupon> patientCoupons = new ArrayList<>();
}
