package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.InstructorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface InstructorRepository extends CrudRepository<InstructorEntity, UUID> {
    List<InstructorEntity> findInstructorEntitiesByFirstName(String firstName);
    List<InstructorEntity> findInstructorEntitiesByLastName(String lastName);
    List<InstructorEntity> findInstructorEntitiesByDateOfBirth(Date dateOfBirth);
    List<InstructorEntity> findInstructorEntitiesByHireDate(Date hireDate);
}
