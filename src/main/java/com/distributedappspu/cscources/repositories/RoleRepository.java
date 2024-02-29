package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<UserRole, UUID> {
    UserRole findUserRoleByName(String name);
}
