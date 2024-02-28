package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.mapper.CourseMapper;
import com.distributedappspu.cscources.models.dto.CourseDTO;
import com.distributedappspu.cscources.models.entities.CourseEntity;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import com.distributedappspu.cscources.repositories.CourseRepository;
import com.distributedappspu.cscources.repositories.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    private InstructorRepository instructorRepository;

    private CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.instructorRepository = instructorRepository;
    }

    public List<CourseDTO> getAllCourses() {
        return courseMapper.mapCourses(this.courseRepository.findAll());
    }

    public CourseDTO getCourseById(UUID id) {
        return courseMapper.mapCourse(courseRepository.findById(id).orElseThrow());
    }

    public List<CourseDTO> getCoursesByName(String name) {
        return courseMapper.mapCourses(courseRepository.findCourseEntitiesByName(name));
    }

    public List<CourseDTO> getCoursesByStartDate(Date startDate) {
        return courseMapper.mapCourses(courseRepository.findCourseEntitiesByStartDate(startDate));
    }

    public List<CourseDTO> getCoursesByEndDate(Date endDate) {
        return courseMapper.mapCourses(courseRepository.findCourseEntitiesByEndDate(endDate));
    }

    public List<CourseDTO> getCoursesByInstructorId(UUID instructorId) {
        Optional<InstructorEntity> instructor = instructorRepository.findById(instructorId);
        if (instructor.isEmpty()) {
            throw new IllegalArgumentException("Instructor with ID " + instructorId + " not found.");
        }
        return courseMapper.mapCourses(courseRepository.findCourseEntitiesByInstructor(instructor.get()));
    }

    public CourseDTO createCourse(@Valid CourseDTO courseDTO){
        CourseEntity studentEntity = courseMapper.mapCourse(courseDTO);
        return courseMapper.mapCourse(courseRepository.save(studentEntity));
    }

    public CourseDTO updateCourse(UUID id, @Valid CourseDTO courseDTO) {

        CourseEntity existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) {
            throw new IllegalArgumentException("Course does not exist!");
        }
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
