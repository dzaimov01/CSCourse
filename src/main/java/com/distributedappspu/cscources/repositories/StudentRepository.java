package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, UUID> {
    Page<StudentEntity> findStudentEntitiesByFirstName(String firstName, Pageable pageable);
    Page<StudentEntity> findStudentEntitiesByLastName(String lastName, Pageable pageable);
    Page<StudentEntity> findStudentEntitiesByEnrollmentDate(Date enrollmentDate, Pageable pageable);
}
