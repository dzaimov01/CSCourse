package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.mapper.CourseMapper;
import com.distributedappspu.cscources.models.dto.CourseDTO;
import com.distributedappspu.cscources.models.entities.CourseEntity;
import com.distributedappspu.cscources.repositories.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    private CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> getAllCourses() {
        return courseMapper.mapCourses(this.courseRepository.findAll());
    }

    public CourseDTO getCourseById(UUID id) {
        return courseMapper.mapCourse(courseRepository.findById(id).orElseThrow());
    }

    public CourseDTO createCourse(@Valid CourseDTO courseDTO){
        //TODO validate input
        CourseEntity studentEntity = courseMapper.mapCourse(courseDTO);
        return courseMapper.mapCourse(courseRepository.save(studentEntity));
    }

    public CourseDTO updateCourse(UUID id, @Valid CourseDTO courseDTO) {

        CourseEntity existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) {
            return null;
            //TODO throw exception
        }

        //TODO validate input

        CourseEntity updatedCourseEntity = courseMapper.mapCourse(courseDTO);
        return courseMapper.mapCourse(courseRepository.save(updatedCourseEntity));
    }

    public void deleteCourse(UUID id) {
        if(!courseRepository.existsById(id)) {
            return;
        }

        courseRepository.deleteById(id);
    }
}
