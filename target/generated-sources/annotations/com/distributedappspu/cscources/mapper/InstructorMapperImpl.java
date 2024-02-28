package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.InstructorDTO;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-28T20:11:55+0200",
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

        return instructorDTO;
    }

    @Override
    public InstructorEntity mapInstructor(InstructorDTO instructorDTO) {
        if ( instructorDTO == null ) {
            return null;
        }

        InstructorEntity instructorEntity = new InstructorEntity();

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
