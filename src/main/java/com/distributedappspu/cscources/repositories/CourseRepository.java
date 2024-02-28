package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.CourseEntity;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, UUID> {
    Page<CourseEntity> findCourseEntitiesByName(String name, Pageable pageable);
    Page<CourseEntity> findCourseEntitiesByStartDate(Date startDate, Pageable pageable);
    Page<CourseEntity> findCourseEntitiesByEndDate(Date endDate, Pageable pageable);
    Page<CourseEntity> findCourseEntitiesByInstructor(InstructorEntity instructor, Pageable pageable);
}
