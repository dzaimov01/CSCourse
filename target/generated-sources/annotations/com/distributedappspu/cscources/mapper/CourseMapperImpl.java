package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.CourseDTO;
import com.distributedappspu.cscources.models.entities.CourseEntity;
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
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO mapCourse(CourseEntity course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setId( course.getId() );
        courseDTO.setName( course.getName() );
        courseDTO.setDescription( course.getDescription() );
        courseDTO.setStartDate( course.getStartDate() );
        courseDTO.setEndDate( course.getEndDate() );

        return courseDTO;
    }

    @Override
    public CourseEntity mapCourse(CourseDTO courseDTO) {
        if ( courseDTO == null ) {
            return null;
        }

        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setId( courseDTO.getId() );
        courseEntity.setName( courseDTO.getName() );
        courseEntity.setDescription( courseDTO.getDescription() );
        courseEntity.setStartDate( courseDTO.getStartDate() );
        courseEntity.setEndDate( courseDTO.getEndDate() );

        return courseEntity;
    }

    @Override
    public List<CourseDTO> mapCourses(Iterable<CourseEntity> courseEntities) {
        if ( courseEntities == null ) {
            return null;
        }

        List<CourseDTO> list = new ArrayList<CourseDTO>();
        for ( CourseEntity courseEntity : courseEntities ) {
            list.add( mapCourse( courseEntity ) );
        }

        return list;
    }
}
