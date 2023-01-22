package com.doctor_appointment.mappers;

import com.doctor_appointment.dto.DoctorDTO;
import com.doctor_appointment.entities.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

	DoctorDTO toDoctorDTO(Doctor doctor);
}
