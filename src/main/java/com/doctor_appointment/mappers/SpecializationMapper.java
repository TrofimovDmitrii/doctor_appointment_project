package com.doctor_appointment.mappers;

import com.doctor_appointment.dto.SpecializationDTO;
import com.doctor_appointment.entities.Specialization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {

	SpecializationDTO toSpecializationDTO(Specialization specialization);
}
