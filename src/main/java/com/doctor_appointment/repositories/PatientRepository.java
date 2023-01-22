package com.doctor_appointment.repositories;

import com.doctor_appointment.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Optional<Patient> getById(int patientId);

	Optional<Patient> getByUuidPatient(UUID uuidPatient);
}
