package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.mapper.InstructorMapper;
import com.distributedappspu.cscources.models.dto.AuthRequestDTO;
import com.distributedappspu.cscources.models.dto.InstructorDTO;
import com.distributedappspu.cscources.models.entities.InstructorEntity;
import com.distributedappspu.cscources.models.entities.UserInfo;
import com.distributedappspu.cscources.repositories.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    private final InstructorMapper instructorMapper;

    private final UserDetailsServiceImpl userDetailsService;

    public InstructorService(InstructorRepository instructorRepository, InstructorMapper instructorMapper, UserDetailsServiceImpl userDetailsService) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
        this.userDetailsService = userDetailsService;
    }

    public List<InstructorDTO> getAllInstructors() {
        return instructorMapper.mapInstructors(instructorRepository.findAll());
    }

    public InstructorDTO getInstructorById(UUID id){
        return instructorMapper.mapInstructor(instructorRepository.findById(id).orElseThrow());
    }

    public Page<InstructorDTO> getInstructorsByFirstName(String firstName, Pageable pageable) {
        Page<InstructorEntity> instructorEntityPage = instructorRepository.findInstructorEntitiesByFirstName(firstName, pageable);
        return instructorEntityPage.map(instructorMapper::mapInstructor);
    }

    public Page<InstructorDTO> getInstructorsByLastName(String lastName, Pageable pageable) {
        Page<InstructorEntity> instructorEntityPage = instructorRepository.findInstructorEntitiesByLastName(lastName, pageable);
        return instructorEntityPage.map(instructorMapper::mapInstructor);
    }

    public Page<InstructorDTO> getInstructorsByDateOfBirth(Date dateOfBirth, Pageable pageable) {
        Page<InstructorEntity> instructorEntityPage = instructorRepository.findInstructorEntitiesByDateOfBirth(dateOfBirth, pageable);
        return instructorEntityPage.map(instructorMapper::mapInstructor);
    }

    public Page<InstructorDTO> getInstructorsByHireDate(Date hireDate, Pageable pageable) {
        Page<InstructorEntity> instructorEntityPage = instructorRepository.findInstructorEntitiesByHireDate(hireDate, pageable);
        return instructorEntityPage.map(instructorMapper::mapInstructor);
    }

    public InstructorDTO createInstructor(@Valid InstructorDTO instructorDTO){
        return addInstructor(instructorDTO);
    }

    public InstructorDTO updateInstructor(UUID id, @Valid InstructorDTO instructorDTO){
        InstructorEntity existingInstructor = instructorRepository.findById(id).orElse(null);
        if (existingInstructor == null) {
            throw new IllegalArgumentException("Instructor does not exist!");
        }
        return addInstructor(instructorDTO);
    }

    private InstructorDTO addInstructor(@Valid InstructorDTO instructorDTO) {
        InstructorEntity instructorEntity = instructorMapper.mapInstructor(instructorDTO);
        UserInfo newUser = userDetailsService.addNewUser(
                new AuthRequestDTO(instructorDTO.getUsername(), instructorDTO.getPassword()), "instructor");
        instructorEntity.setUserInfo(newUser);
        InstructorEntity newInstructorEntity = instructorRepository.save(instructorEntity);
        InstructorDTO newInstructorDTO = instructorMapper.mapInstructor(newInstructorEntity);
        newInstructorDTO.setUsername(newInstructorEntity.getUserInfo().getUsername());
        newInstructorDTO.setPassword(newInstructorEntity.getUserInfo().getPassword());
        return newInstructorDTO;
    }

    public void deleteInstructor(UUID id){
        if(!instructorRepository.existsById(id)){
            return;
        }
        instructorRepository.deleteById(id);
    }
}
