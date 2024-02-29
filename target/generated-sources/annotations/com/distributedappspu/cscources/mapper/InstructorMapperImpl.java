package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.InstructorDTO;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-29T20:27:19+0200",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class InstructorMapperImpl implements InstructorMapper {

    @Override
    public InstructorDTO mapInstructor(InstructorEntity instructor) {
        if ( instructor == null ) {
            return null;
        }

        InstructorDTO instructorDTO = new InstructorDTO();

        instructorDTO.setId( instructor.getId() );
        instructorDTO.setFirstName( instructor.getFirstName() );
        instructorDTO.setLastName( instructor.getLastName() );
        instructorDTO.setEmail( instructor.getEmail() );
        instructorDTO.setDateOfBirth( instructor.getDateOfBirth() );
        instructorDTO.setHireDate( instructor.getHireDate() );

        return instructorDTO;
    }

    @Override
    public InstructorEntity mapInstructor(InstructorDTO instructorDTO) {
        if ( instructorDTO == null ) {
            return null;
        }

        InstructorEntity instructorEntity = new InstructorEntity();

        instructorEntity.setId( instructorDTO.getId() );
        instructorEntity.setFirstName( instructorDTO.getFirstName() );
        instructorEntity.setLastName( instructorDTO.getLastName() );
        instructorEntity.setEmail( instructorDTO.getEmail() );
        instructorEntity.setDateOfBirth( instructorDTO.getDateOfBirth() );
        instructorEntity.setHireDate( instructorDTO.getHireDate() );

        return instructorEntity;
    }

    @Override
    public List<InstructorDTO> mapInstructors(Iterable<InstructorEntity> instructorEntities) {
        if ( instructorEntities == null ) {
            return null;
        }

        List<InstructorDTO> list = new ArrayList<InstructorDTO>();
        for ( InstructorEntity instructorEntity : instructorEntities ) {
            list.add( mapInstructor( instructorEntity ) );
        }

        return list;
    }
}
