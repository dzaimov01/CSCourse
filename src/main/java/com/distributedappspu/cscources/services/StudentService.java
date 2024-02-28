package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.mapper.StudentMapper;
import com.distributedappspu.cscources.models.dto.StudentDTO;
import com.distributedappspu.cscources.models.entities.StudentEntity;
import com.distributedappspu.cscources.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper){
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDTO> getAllStudents() {
        return studentMapper.mapStudents(studentRepository.findAll());
    }

    public StudentDTO getStudentById(UUID id){
        return studentMapper.mapStudent(studentRepository.findById(id).orElseThrow());
    }

    public Page<StudentDTO> getStudentsByFirstName(String firstName, Pageable pageable) {
        Page<StudentEntity> studentEntityPage = studentRepository.findStudentEntitiesByFirstName(firstName, pageable);
        return studentEntityPage.map(studentMapper::mapStudent);
    }

    public Page<StudentDTO> getStudentsByLastName(String lastName, Pageable pageable) {
        Page<StudentEntity> studentEntityPage = studentRepository.findStudentEntitiesByLastName(lastName, pageable);
        return studentEntityPage.map(studentMapper::mapStudent);
    }

    public Page<StudentDTO> getStudentsByEnrollmentDate(Date enrollmentDate, Pageable pageable) {
        Page<StudentEntity> studentEntityPage = studentRepository.findStudentEntitiesByEnrollmentDate(enrollmentDate, pageable);
        return studentEntityPage.map(studentMapper::mapStudent);
    }

    public StudentDTO createStudent(@Valid StudentDTO studentDTO) {
        StudentEntity instructorEntity = studentMapper.mapStudent(studentDTO);
        return studentMapper.mapStudent(studentRepository.save(instructorEntity));
    }

    public StudentDTO updateStudent(UUID id, @Valid StudentDTO studentDTO){
        StudentEntity existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            throw new IllegalArgumentException("Student does not exist!");
        }
        StudentEntity updatedStudentEntity = studentMapper.mapStudent(studentDTO);
        return studentMapper.mapStudent(studentRepository.save(updatedStudentEntity));
    }

    public void deleteStudent(UUID id){
        if(!studentRepository.existsById(id)) {
            return;
        }
        studentRepository.deleteById(id);
    }
}
