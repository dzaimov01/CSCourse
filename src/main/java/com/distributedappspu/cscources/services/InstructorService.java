package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.mapper.InstructorMapper;
import com.distributedappspu.cscources.models.dto.InstructorDTO;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import com.distributedappspu.cscources.repositories.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InstructorService {

    private InstructorRepository instructorRepository;

    private InstructorMapper instructorMapper;

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

    public InstructorDTO createInstructor(@Valid InstructorDTO instructorDTO){
        //TODO validate input
        InstructorEntity instructorEntity = instructorMapper.mapInstructor(instructorDTO);
        return instructorMapper.mapInstructor(instructorRepository.save(instructorEntity));
    }

    public InstructorDTO updateInstructor(UUID id, @Valid InstructorDTO instructorDTO){
        InstructorEntity existingInstructor = instructorRepository.findById(id).orElse(null);
        if (existingInstructor == null) {
            return null;
            //TODO throw exception
        }

        //TODO validate input

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
