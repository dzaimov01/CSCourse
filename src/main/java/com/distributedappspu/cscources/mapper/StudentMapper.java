package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.StudentDTO;
import com.distributedappspu.cscources.models.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    StudentDTO mapStudent(StudentEntity student);

    @Mapping(target = "userInfo", ignore = true)
    StudentEntity mapStudent(StudentDTO studentDTO);

    List<StudentDTO> mapStudents(Iterable<StudentEntity> studentEntities);
}
