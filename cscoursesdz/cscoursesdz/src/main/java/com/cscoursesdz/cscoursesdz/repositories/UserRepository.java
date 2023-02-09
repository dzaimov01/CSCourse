package com.cscoursesdz.cscoursesdz.repositories;

import com.cscoursesdz.cscoursesdz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findOneById(UUID id);
    Optional<User> findByEmail(String email);
    Optional<User> findOneWithAuthoritiesByEmail(String lowercaseEmail);
    Boolean existsByEmail(String email);
}
