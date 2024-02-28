package com.distributedappspu.cscources.controllers;

import com.distributedappspu.cscources.models.dto.InstructorDTO;
import com.distributedappspu.cscources.services.InstructorService;
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
@RequestMapping("v1/instructors")
@Validated
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<InstructorDTO> createInstructor(@Valid @RequestBody InstructorDTO instructorDTO) {
        InstructorDTO createdInstructor = instructorService.createInstructor(instructorDTO);
        return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable UUID id) {
        InstructorDTO instructorDTO = instructorService.getInstructorById(id);
        return new ResponseEntity<>(instructorDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAllInstructors() {
        List<InstructorDTO> instructorDTOs = instructorService.getAllInstructors();
        return new ResponseEntity<>(instructorDTOs, HttpStatus.OK);
    }

    @GetMapping("/search/byFirstName")
    public Page<InstructorDTO> findByFirstName(@RequestParam String firstName, @PageableDefault() Pageable pageable) {
        return instructorService.getInstructorsByFirstName(firstName, pageable);
    }

    @GetMapping("/search/byLastName")
    public Page<InstructorDTO> findByLastName(@RequestParam String lastName, @PageableDefault() Pageable pageable) {
        return instructorService.getInstructorsByLastName(lastName, pageable);
    }

    @GetMapping("/search/byDateOfBirth")
    public Page<InstructorDTO> findByDateOfBirth(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfBirth, @PageableDefault() Pageable pageable) {
        return instructorService.getInstructorsByDateOfBirth(dateOfBirth, pageable);
    }

    @GetMapping("/search/byHireDate")
    public Page<InstructorDTO> findByHireDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hireDate, @PageableDefault() Pageable pageable) {
        return instructorService.getInstructorsByHireDate(hireDate, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable UUID id, @Valid @RequestBody InstructorDTO instructorDTO) {
        InstructorDTO updatedInstructor = instructorService.updateInstructor(id, instructorDTO);
        return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable UUID id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}

