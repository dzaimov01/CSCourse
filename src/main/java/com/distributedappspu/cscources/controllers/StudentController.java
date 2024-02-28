package com.distributedappspu.cscources.controllers;

import com.distributedappspu.cscources.models.dto.StudentDTO;
import com.distributedappspu.cscources.services.StudentService;
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
@RequestMapping("v1/students")
@Validated
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable UUID id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentDTOs = studentService.getAllStudents();
        return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
    }

    @GetMapping("/search/byFirstName")
    public Page<StudentDTO> getStudentsByFirstName(@RequestParam String firstName, @PageableDefault() Pageable pageable) {
        return studentService.getStudentsByFirstName(firstName, pageable);
    }

    @GetMapping("/search/byLastName")
    public Page<StudentDTO> getStudentsByLastName(@RequestParam String lastName, @PageableDefault() Pageable pageable) {
        return studentService.getStudentsByLastName(lastName, pageable);
    }

    @GetMapping("/search/byEnrollmentDate")
    public Page<StudentDTO> getStudentsByEnrollmentDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date enrollmentDate, @PageableDefault() Pageable pageable) {
        return studentService.getStudentsByEnrollmentDate(enrollmentDate, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable UUID id, @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}

