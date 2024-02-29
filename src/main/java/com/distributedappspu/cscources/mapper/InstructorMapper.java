package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.InstructorDTO;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    InstructorDTO mapInstructor(InstructorEntity instructor);

    @Mapping(target = "userInfo", ignore = true)
    InstructorEntity mapInstructor(InstructorDTO instructorDTO);

    List<InstructorDTO> mapInstructors(Iterable<InstructorEntity> instructorEntities);
}
