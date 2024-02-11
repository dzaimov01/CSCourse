package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.StudentDTO;
import com.distributedappspu.cscources.models.entities.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO mapStudent(StudentEntity student);

    StudentEntity mapStudent(StudentDTO studentDTO);
}
