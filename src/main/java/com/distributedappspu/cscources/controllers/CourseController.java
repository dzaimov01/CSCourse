package com.distributedappspu.cscources.controllers;

import com.distributedappspu.cscources.models.dto.CourseDTO;
import com.distributedappspu.cscources.services.CourseService;
import jakarta.validation.Valid;
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

    @PostMapping
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
    public ResponseEntity<List<CourseDTO>> getCoursesByName(@RequestParam String name) {
        List<CourseDTO> courses = courseService.getCoursesByName(name);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/byStartDate")
    public ResponseEntity<List<CourseDTO>> getCoursesByStartDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate) {
        List<CourseDTO> courses = courseService.getCoursesByStartDate(startDate);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/byEndDate")
    public ResponseEntity<List<CourseDTO>> getCoursesByEndDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<CourseDTO> courses = courseService.getCoursesByEndDate(endDate);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/byInstructor/{instructorId}")
    public ResponseEntity<List<CourseDTO>> getCoursesByInstructorId(@PathVariable UUID instructorId) {
        List<CourseDTO> courses = courseService.getCoursesByInstructorId(instructorId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping
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

