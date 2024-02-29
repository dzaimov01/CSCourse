package com.distributedappspu.cscources.mapper;

import com.distributedappspu.cscources.models.dto.CourseDTO;
import com.distributedappspu.cscources.models.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "instructorId", ignore = true)
    @Mapping(target = "students", ignore = true)
    CourseDTO mapCourse(CourseEntity course);

    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "students", ignore = true)
    CourseEntity mapCourse(CourseDTO courseDTO);

    List<CourseDTO> mapCourses(Iterable<CourseEntity> courseEntities);
}
