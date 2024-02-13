package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, UUID> {
}
