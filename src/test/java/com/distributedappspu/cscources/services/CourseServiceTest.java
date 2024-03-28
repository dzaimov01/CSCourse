package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.models.dto.CourseDTO;
import com.distributedappspu.cscources.models.entities.CourseEntity;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import com.distributedappspu.cscources.repositories.CourseRepository;
import com.distributedappspu.cscources.repositories.InstructorRepository;
import com.distributedappspu.cscources.services.CourseService;
import com.distributedappspu.cscources.mapper.CourseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCourses() {
        when(courseRepository.findAll()).thenReturn(Collections.singletonList(new CourseEntity()));
        when(courseMapper.mapCourses(anyList())).thenReturn(Collections.singletonList(new CourseDTO()));

        List<CourseDTO> result = courseService.getAllCourses();

        verify(courseRepository).findAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void getCourseById() {
        UUID id = UUID.randomUUID();
        when(courseRepository.findById(id)).thenReturn(Optional.of(new CourseEntity()));
        when(courseMapper.mapCourse(any(CourseEntity.class))).thenReturn(new CourseDTO());

        CourseDTO result = courseService.getCourseById(id);

        verify(courseRepository).findById(id);
        assertNotNull(result);
    }

    @Test
    void getCoursesByName() {
        String name = "Test Course";
        Page<CourseEntity> mockPage = new PageImpl<>(Collections.singletonList(new CourseEntity()));
        when(courseRepository.findCourseEntitiesByName(eq(name), any(Pageable.class))).thenReturn(mockPage);
        when(courseMapper.mapCourse(any(CourseEntity.class))).thenReturn(new CourseDTO());

        Page<CourseDTO> result = courseService.getCoursesByName(name, Pageable.unpaged());

        verify(courseRepository).findCourseEntitiesByName(eq(name), any(Pageable.class));
        assertFalse(result.isEmpty());
    }

    @Test
    void getCoursesByStartDate() {
        Date startDate = new Date();
        Page<CourseEntity> mockPage = new PageImpl<>(Collections.singletonList(new CourseEntity()));
        when(courseRepository.findCourseEntitiesByStartDate(eq(startDate), any(Pageable.class))).thenReturn(mockPage);
        when(courseMapper.mapCourse(any(CourseEntity.class))).thenReturn(new CourseDTO());

        Page<CourseDTO> result = courseService.getCoursesByStartDate(startDate, Pageable.unpaged());

        verify(courseRepository).findCourseEntitiesByStartDate(eq(startDate), any(Pageable.class));
        assertFalse(result.isEmpty());
    }

    @Test
    void getCoursesByEndDate() {
        Date endDate = new Date();
        Page<CourseEntity> mockPage = new PageImpl<>(Collections.singletonList(new CourseEntity()));
        when(courseRepository.findCourseEntitiesByEndDate(eq(endDate), any(Pageable.class))).thenReturn(mockPage);
        when(courseMapper.mapCourse(any(CourseEntity.class))).thenReturn(new CourseDTO());

        Page<CourseDTO> result = courseService.getCoursesByEndDate(endDate, Pageable.unpaged());

        verify(courseRepository).findCourseEntitiesByEndDate(eq(endDate), any(Pageable.class));
        assertFalse(result.isEmpty());
    }

    @Test
    void getCoursesByInstructorId() {
        UUID instructorId = UUID.randomUUID();
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setId(instructorId);
        Page<CourseEntity> mockPage = new PageImpl<>(Collections.singletonList(new CourseEntity()));
        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructorEntity));
        when(courseRepository.findCourseEntitiesByInstructor(eq(instructorEntity), any(Pageable.class))).thenReturn(mockPage);
        when(courseMapper.mapCourse(any(CourseEntity.class))).thenReturn(new CourseDTO());

        Page<CourseDTO> result = courseService.getCoursesByInstructorId(instructorId, Pageable.unpaged());

        verify(instructorRepository).findById(instructorId);
        verify(courseRepository).findCourseEntitiesByInstructor(eq(instructorEntity), any(Pageable.class));
        assertFalse(result.isEmpty());
    }

    @Test
    void deleteCourse() {
        UUID id = UUID.randomUUID();
        when(courseRepository.existsById(id)).thenReturn(true);

        courseService.deleteCourse(id);

        verify(courseRepository).deleteById(id);
    }
}

