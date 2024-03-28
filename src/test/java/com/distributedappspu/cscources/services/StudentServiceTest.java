package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.mapper.StudentMapper;
import com.distributedappspu.cscources.models.dto.StudentDTO;
import com.distributedappspu.cscources.models.entities.StudentEntity;
import com.distributedappspu.cscources.models.entities.UserInfo;
import com.distributedappspu.cscources.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStudentById() {
        UUID id = UUID.randomUUID();
        StudentEntity mockStudentEntity = new StudentEntity();
        when(studentRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockStudentEntity));
        when(studentMapper.mapStudent(any(StudentEntity.class))).thenReturn(new StudentDTO());

        StudentDTO result = studentService.getStudentById(id);

        verify(studentRepository).findById(id);
        verify(studentMapper).mapStudent(mockStudentEntity);
        assertNotNull(result);
    }

    @Test
    void getStudentsByFirstName() {
        Page<StudentEntity> mockPage = new PageImpl<>(Collections.singletonList(new StudentEntity()));
        when(studentRepository.findStudentEntitiesByFirstName(anyString(), any(Pageable.class))).thenReturn(mockPage);
        when(studentMapper.mapStudent(any(StudentEntity.class))).thenReturn(new StudentDTO());

        Page<StudentDTO> result = studentService.getStudentsByFirstName("John", Pageable.unpaged());

        verify(studentRepository).findStudentEntitiesByFirstName(eq("John"), any(Pageable.class));
        assertFalse(result.isEmpty());
    }

    @Test
    void getStudentsByLastName() {
        String lastName = "Doe";
        Page<StudentEntity> mockPage = new PageImpl<>(Collections.singletonList(new StudentEntity()));
        when(studentRepository.findStudentEntitiesByLastName(eq(lastName), any(Pageable.class))).thenReturn(mockPage);
        when(studentMapper.mapStudent(any(StudentEntity.class))).thenReturn(new StudentDTO());

        Page<StudentDTO> result = studentService.getStudentsByLastName(lastName, Pageable.unpaged());

        verify(studentRepository).findStudentEntitiesByLastName(eq(lastName), any(Pageable.class));
        assertFalse(result.isEmpty());
        assertEquals(1, result.getContent().size()); // Assuming there's 1 student for simplicity
    }


    @Test
    void getStudentsByEnrollmentDate() {
        Date enrollmentDate = new Date();
        Page<StudentEntity> mockPage = new PageImpl<>(Collections.singletonList(new StudentEntity()));
        when(studentRepository.findStudentEntitiesByEnrollmentDate(eq(enrollmentDate), any(Pageable.class))).thenReturn(mockPage);
        when(studentMapper.mapStudent(any(StudentEntity.class))).thenReturn(new StudentDTO());

        Page<StudentDTO> result = studentService.getStudentsByEnrollmentDate(enrollmentDate, Pageable.unpaged());

        verify(studentRepository).findStudentEntitiesByEnrollmentDate(eq(enrollmentDate), any(Pageable.class));
        assertFalse(result.isEmpty());
        assertEquals(1, result.getContent().size()); // Assuming there's 1 student for simplicity
    }

    @Test
    void deleteStudent() {
        UUID id = UUID.randomUUID();
        when(studentRepository.existsById(id)).thenReturn(true);

        studentService.deleteStudent(id);

        verify(studentRepository).deleteById(id);
    }
}

