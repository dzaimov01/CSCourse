package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, UUID> {
    List<StudentEntity> findStudentEntitiesByFirstName(String firstName);
    List<StudentEntity> findStudentEntitiesByLastName(String lastName);
    List<StudentEntity> findStudentEntitiesByEnrollmentDate(Date enrollmentDate);
}
