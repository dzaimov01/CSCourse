package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.mapper.InstructorMapper;
import com.distributedappspu.cscources.models.dto.InstructorDTO;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import com.distributedappspu.cscources.repositories.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    private final InstructorMapper instructorMapper;

    public InstructorService(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }

    public List<InstructorDTO> getAllInstructors() {
        return instructorMapper.mapInstructors(instructorRepository.findAll());
    }

    public InstructorDTO getInstructorById(UUID id){
        return instructorMapper.mapInstructor(instructorRepository.findById(id).orElseThrow());
    }

    public List<InstructorDTO> getInstructorsByFirstName(String firstName) {
        return instructorMapper.mapInstructors(instructorRepository.findInstructorEntitiesByFirstName(firstName));
    }

    public List<InstructorDTO> getInstructorsByLastName(String lastName) {
        return instructorMapper.mapInstructors(instructorRepository.findInstructorEntitiesByLastName(lastName));
    }

    public List<InstructorDTO> getInstructorsByDateOfBirth(Date dateOfBirth) {
        return instructorMapper.mapInstructors(instructorRepository.findInstructorEntitiesByDateOfBirth(dateOfBirth));
    }

    public List<InstructorDTO> getInstructorsByHireDate(Date hireDate) {
        return instructorMapper.mapInstructors(instructorRepository.findInstructorEntitiesByHireDate(hireDate));
    }

    public InstructorDTO createInstructor(@Valid InstructorDTO instructorDTO){
        InstructorEntity instructorEntity = instructorMapper.mapInstructor(instructorDTO);
        return instructorMapper.mapInstructor(instructorRepository.save(instructorEntity));
    }

    public InstructorDTO updateInstructor(UUID id, @Valid InstructorDTO instructorDTO){
        InstructorEntity existingInstructor = instructorRepository.findById(id).orElse(null);
        if (existingInstructor == null) {
            throw new IllegalArgumentException("Instructor does not exist!");
        }
        InstructorEntity updatedInstructorEntity = instructorMapper.mapInstructor(instructorDTO);
        return instructorMapper.mapInstructor(instructorRepository.save(updatedInstructorEntity));
    }

    public void deleteInstructor(UUID id){
        if(!instructorRepository.existsById(id)){
            return;
        }
        instructorRepository.deleteById(id);
    }
}
