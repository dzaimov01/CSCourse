package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.InstructorDTO;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    InstructorDTO mapInstructor(InstructorEntity instructor);

    InstructorEntity mapInstructor(InstructorDTO instructorDTO);

    List<InstructorDTO> mapInstructors(Iterable<InstructorEntity> instructorEntities);
}
