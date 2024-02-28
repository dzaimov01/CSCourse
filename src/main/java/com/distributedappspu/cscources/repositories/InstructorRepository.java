package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.InstructorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface InstructorRepository extends CrudRepository<InstructorEntity, UUID> {
    Page<InstructorEntity> findInstructorEntitiesByFirstName(String firstName, Pageable pageable);
    Page<InstructorEntity> findInstructorEntitiesByLastName(String lastName, Pageable pageable);
    Page<InstructorEntity> findInstructorEntitiesByDateOfBirth(Date dateOfBirth, Pageable pageable);
    Page<InstructorEntity> findInstructorEntitiesByHireDate(Date hireDate, Pageable pageable);
}
