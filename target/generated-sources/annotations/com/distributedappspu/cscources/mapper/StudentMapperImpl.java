package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.StudentDTO;
import com.distributedappspu.cscources.models.entities.StudentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-28T20:11:54+0200",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO mapStudent(StudentEntity student) {
        if ( student == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        return studentDTO;
    }

    @Override
    public StudentEntity mapStudent(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        return studentEntity;
    }

    @Override
    public List<StudentDTO> mapStudents(Iterable<StudentEntity> studentEntities) {
        if ( studentEntities == null ) {
            return null;
        }

        List<StudentDTO> list = new ArrayList<StudentDTO>();
        for ( StudentEntity studentEntity : studentEntities ) {
            list.add( mapStudent( studentEntity ) );
        }

        return list;
    }
}
