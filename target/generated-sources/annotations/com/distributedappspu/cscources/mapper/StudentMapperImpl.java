package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.StudentDTO;
import com.distributedappspu.cscources.models.entities.StudentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-16T11:05:56+0200",
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

        studentDTO.setId( student.getId() );
        studentDTO.setFirstName( student.getFirstName() );
        studentDTO.setLastName( student.getLastName() );
        studentDTO.setEmail( student.getEmail() );
        studentDTO.setDateOfBirth( student.getDateOfBirth() );
        studentDTO.setEnrollmentDate( student.getEnrollmentDate() );

        return studentDTO;
    }

    @Override
    public StudentEntity mapStudent(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setId( studentDTO.getId() );
        studentEntity.setFirstName( studentDTO.getFirstName() );
        studentEntity.setLastName( studentDTO.getLastName() );
        studentEntity.setEmail( studentDTO.getEmail() );
        studentEntity.setDateOfBirth( studentDTO.getDateOfBirth() );
        studentEntity.setEnrollmentDate( studentDTO.getEnrollmentDate() );

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
