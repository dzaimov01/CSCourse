package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.CourseDTO;
import com.distributedappspu.cscources.models.entities.CourseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO mapCourse(CourseEntity course);

    CourseEntity mapCourse(CourseDTO courseDTO);

    List<CourseDTO> mapCourses(Iterable<CourseEntity> courseEntities);
}
