package com.distributedappspu.cscources.controllers;

import com.distributedappspu.cscources.models.dto.CourseDTO;
import com.distributedappspu.cscources.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/courses")
@Validated
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/createCourse")
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable UUID id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping("/byName")
    public ResponseEntity<Page<CourseDTO>> getCoursesByName(@RequestParam String name, @PageableDefault() Pageable pageable) {
        Page<CourseDTO> courses = courseService.getCoursesByName(name, pageable);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/byStartDate")
    public ResponseEntity<Page<CourseDTO>> getCoursesByStartDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @PageableDefault() Pageable pageable) {
        Page<CourseDTO> courses = courseService.getCoursesByStartDate(startDate, pageable);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/byEndDate")
    public ResponseEntity<Page<CourseDTO>> getCoursesByEndDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate, @PageableDefault() Pageable pageable) {
        Page<CourseDTO> courses = courseService.getCoursesByEndDate(endDate, pageable);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/byInstructor/{instructorId}")
    public ResponseEntity<Page<CourseDTO>> getCoursesByInstructorId(@PathVariable UUID instructorId, @PageableDefault() Pageable pageable) {
        Page<CourseDTO> courses = courseService.getCoursesByInstructorId(instructorId, pageable);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courseDTOs = courseService.getAllCourses();
        return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable UUID id, @Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

