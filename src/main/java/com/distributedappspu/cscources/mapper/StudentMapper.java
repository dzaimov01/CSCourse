package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.StudentDTO;
import com.distributedappspu.cscources.models.entities.StudentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO mapStudent(StudentEntity student);

    StudentEntity mapStudent(StudentDTO studentDTO);

    List<StudentDTO> mapStudents(Iterable<StudentEntity> studentEntities);
}
