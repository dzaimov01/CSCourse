package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.CourseEntity;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, UUID> {
    List<CourseEntity> findCourseEntitiesByName(String name);
    List<CourseEntity> findCourseEntitiesByStartDate(Date startDate);
    List<CourseEntity> findCourseEntitiesByEndDate(Date endDate);
    List<CourseEntity> findCourseEntitiesByInstructor(InstructorEntity instructor);
}
