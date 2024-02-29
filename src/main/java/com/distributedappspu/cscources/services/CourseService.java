package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.mapper.CourseMapper;
import com.distributedappspu.cscources.models.dto.CourseDTO;
import com.distributedappspu.cscources.models.entities.CourseEntity;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import com.distributedappspu.cscources.repositories.CourseRepository;
import com.distributedappspu.cscources.repositories.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final InstructorRepository instructorRepository;

    private final CourseMapper courseMapper;

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

    public Page<CourseDTO> getCoursesByName(String name, Pageable pageable) {
        Page<CourseEntity> courseEntityPage = courseRepository.findCourseEntitiesByName(name, pageable);
        return courseEntityPage.map(courseMapper::mapCourse);
    }

    public Page<CourseDTO> getCoursesByStartDate(Date startDate, Pageable pageable) {
        Page<CourseEntity> courseEntityPage = courseRepository.findCourseEntitiesByStartDate(startDate, pageable);
        return courseEntityPage.map(courseMapper::mapCourse);
    }

    public Page<CourseDTO> getCoursesByEndDate(Date endDate, Pageable pageable) {
        Page<CourseEntity> courseEntityPage = courseRepository.findCourseEntitiesByEndDate(endDate, pageable);
        return courseEntityPage.map(courseMapper::mapCourse);
    }

    public Page<CourseDTO> getCoursesByInstructorId(UUID instructorId, Pageable pageable) {
        Optional<InstructorEntity> instructor = instructorRepository.findById(instructorId);
        if (instructor.isEmpty()) {
            throw new IllegalArgumentException("Instructor with ID " + instructorId + " not found.");
        }
        Page<CourseEntity> courseEntityPage = courseRepository.findCourseEntitiesByInstructor(instructor.get(), pageable);
        return courseEntityPage.map(courseMapper::mapCourse);
    }

    public CourseDTO createCourse(@Valid CourseDTO courseDTO){
        return addCourse(courseDTO);
    }

    public CourseDTO updateCourse(UUID id, @Valid CourseDTO courseDTO) {

        CourseEntity existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) {
            throw new IllegalArgumentException("Course does not exist!");
        }
        return addCourse(courseDTO);
    }

    private CourseDTO addCourse(@Valid CourseDTO courseDTO) {
        CourseEntity courseEntity = courseMapper.mapCourse(courseDTO);
        InstructorEntity instructor = instructorRepository.findById(courseDTO.getInstructorId()).orElse(null);
        courseEntity.setInstructor(instructor);
        CourseEntity newCourse = courseRepository.save(courseEntity);
        CourseDTO newCourseDTO = courseMapper.mapCourse(newCourse);
        newCourseDTO.setInstructorId(courseEntity.getInstructor().getId());
        return newCourseDTO;
    }

    public void deleteCourse(UUID id) {
        if(!courseRepository.existsById(id)) {
            return;
        }
        courseRepository.deleteById(id);
    }
}
