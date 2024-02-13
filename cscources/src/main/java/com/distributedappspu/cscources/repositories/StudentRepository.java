package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, UUID> {
}
