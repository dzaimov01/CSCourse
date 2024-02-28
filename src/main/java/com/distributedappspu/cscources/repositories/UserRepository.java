package com.distributedappspu.cscources.repositories;

import com.distributedappspu.cscources.models.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, UUID> {
    UserInfo findByUsername(String username);
}
