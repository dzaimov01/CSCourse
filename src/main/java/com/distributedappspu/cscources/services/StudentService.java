package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.mapper.StudentMapper;
import com.distributedappspu.cscources.models.dto.AuthRequestDTO;
import com.distributedappspu.cscources.models.dto.StudentDTO;
import com.distributedappspu.cscources.models.entities.StudentEntity;
import com.distributedappspu.cscources.models.entities.UserInfo;
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

    private final UserDetailsServiceImpl userDetailsService;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, UserDetailsServiceImpl userDetailsService){
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.userDetailsService = userDetailsService;
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
        return addStudent(studentDTO);
    }

    public StudentDTO updateStudent(UUID id, @Valid StudentDTO studentDTO){
        StudentEntity existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            throw new IllegalArgumentException("Student does not exist!");
        }
        return addStudent(studentDTO);
    }

    private StudentDTO addStudent(@Valid StudentDTO studentDTO) {
        StudentEntity studentEntity = studentMapper.mapStudent(studentDTO);
        UserInfo authRequestDTO = userDetailsService.addNewUser(
                new AuthRequestDTO(studentDTO.getUsername(), studentDTO.getPassword()), "student");
        studentEntity.setUserInfo(authRequestDTO);
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        StudentDTO savedStudentDTO = studentMapper.mapStudent(savedStudentEntity);
        savedStudentDTO.setUsername(savedStudentEntity.getUserInfo().getUsername());
        savedStudentDTO.setPassword(savedStudentEntity.getUserInfo().getPassword());
        return savedStudentDTO;
    }

    public void deleteStudent(UUID id){
        if(!studentRepository.existsById(id)) {
            return;
        }
        studentRepository.deleteById(id);
    }
}
