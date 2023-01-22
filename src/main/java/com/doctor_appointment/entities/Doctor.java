package com.doctor_appointment.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank
	@Column(name = "uuid_doctor")
	private UUID uuidDoctor;

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

	@ManyToOne(optional = false)
	@JoinColumn(name = "specialization_id")
	@ToString.Exclude
	private Specialization specializationId;
}
