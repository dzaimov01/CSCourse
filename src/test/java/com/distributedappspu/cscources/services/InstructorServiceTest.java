package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.models.dto.InstructorDTO;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import com.distributedappspu.cscources.repositories.InstructorRepository;
import com.distributedappspu.cscources.services.InstructorService;
import com.distributedappspu.cscources.mapper.InstructorMapper;
import com.distributedappspu.cscources.services.UserDetailsServiceImpl;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private InstructorMapper instructorMapper;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private InstructorService instructorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllInstructors() {
        when(instructorRepository.findAll()).thenReturn(Collections.singletonList(new InstructorEntity()));
        when(instructorMapper.mapInstructors(anyList())).thenReturn(Collections.singletonList(new InstructorDTO()));

        List<InstructorDTO> result = instructorService.getAllInstructors();

        verify(instructorRepository).findAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void getInstructorById() {
        UUID id = UUID.randomUUID();
        when(instructorRepository.findById(id)).thenReturn(Optional.of(new InstructorEntity()));
        when(instructorMapper.mapInstructor(any(InstructorEntity.class))).thenReturn(new InstructorDTO());

        InstructorDTO result = instructorService.getInstructorById(id);

        verify(instructorRepository).findById(id);
        assertNotNull(result);
    }

    @Test
    void getInstructorsByFirstName() {
        Page<InstructorEntity> mockPage = new PageImpl<>(Collections.singletonList(new InstructorEntity()));
        when(instructorRepository.findInstructorEntitiesByFirstName(anyString(), any(Pageable.class))).thenReturn(mockPage);
        when(instructorMapper.mapInstructor(any(InstructorEntity.class))).thenReturn(new InstructorDTO());

        Page<InstructorDTO> result = instructorService.getInstructorsByFirstName("John", Pageable.unpaged());

        verify(instructorRepository).findInstructorEntitiesByFirstName(anyString(), any(Pageable.class));
        assertFalse(result.isEmpty());
    }

    @Test
    void getInstructorsByLastName() {
        String lastName = "Smith";
        Page<InstructorEntity> mockPage = new PageImpl<>(Collections.singletonList(new InstructorEntity()));
        when(instructorRepository.findInstructorEntitiesByLastName(eq(lastName), any(Pageable.class))).thenReturn(mockPage);
        when(instructorMapper.mapInstructor(any(InstructorEntity.class))).thenReturn(new InstructorDTO());

        Page<InstructorDTO> result = instructorService.getInstructorsByLastName(lastName, Pageable.unpaged());

        verify(instructorRepository).findInstructorEntitiesByLastName(eq(lastName), any(Pageable.class));
        assertFalse(result.isEmpty());
    }

    @Test
    void getInstructorsByDateOfBirth() {
        Date dateOfBirth = new Date();
        Page<InstructorEntity> mockPage = new PageImpl<>(Collections.singletonList(new InstructorEntity()));
        when(instructorRepository.findInstructorEntitiesByDateOfBirth(eq(dateOfBirth), any(Pageable.class))).thenReturn(mockPage);
        when(instructorMapper.mapInstructor(any(InstructorEntity.class))).thenReturn(new InstructorDTO());

        Page<InstructorDTO> result = instructorService.getInstructorsByDateOfBirth(dateOfBirth, Pageable.unpaged());

        verify(instructorRepository).findInstructorEntitiesByDateOfBirth(eq(dateOfBirth), any(Pageable.class));
        assertFalse(result.isEmpty());
    }

    @Test
    void getInstructorsByHireDate() {
        Date hireDate = new Date();
        Page<InstructorEntity> mockPage = new PageImpl<>(Collections.singletonList(new InstructorEntity()));
        when(instructorRepository.findInstructorEntitiesByHireDate(eq(hireDate), any(Pageable.class))).thenReturn(mockPage);
        when(instructorMapper.mapInstructor(any(InstructorEntity.class))).thenReturn(new InstructorDTO());

        Page<InstructorDTO> result = instructorService.getInstructorsByHireDate(hireDate, Pageable.unpaged());

        verify(instructorRepository).findInstructorEntitiesByHireDate(eq(hireDate), any(Pageable.class));
        assertFalse(result.isEmpty());
    }

    @Test
    void deleteInstructor() {
        UUID id = UUID.randomUUID();
        when(instructorRepository.existsById(id)).thenReturn(true);

        instructorService.deleteInstructor(id);

        verify(instructorRepository).deleteById(id);
    }
}

