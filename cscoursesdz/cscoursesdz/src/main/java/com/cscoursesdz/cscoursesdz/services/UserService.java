package com.cscoursesdz.cscoursesdz.services;

import com.cscoursesdz.cscoursesdz.common.AuthProvider;
import com.cscoursesdz.cscoursesdz.common.AuthoritiesConstants;
import com.cscoursesdz.cscoursesdz.dto.UserDTO;
import com.cscoursesdz.cscoursesdz.exceptions.ApiException;
import com.cscoursesdz.cscoursesdz.models.Role;
import com.cscoursesdz.cscoursesdz.models.User;
import com.cscoursesdz.cscoursesdz.repositories.RoleRepository;
import com.cscoursesdz.cscoursesdz.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public User registerAccount(UserDTO.Create userDto) {
        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new ApiException("Email Already exists.", HttpStatus.BAD_REQUEST);
                });

        return this.createUser(userDto.getEmail().toLowerCase(), userDto.getPassword(), userDto.getUserName());
    }

    public User createUser(String email, String password, String userName) {
        User newUser = new User();
        Optional<Role> authority = roleRepository.findById(UUID.fromString(AuthoritiesConstants.USER));
        Set<Role> authorities = new HashSet<>();
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setUserName(userName);
        newUser.setProvider(AuthProvider.local);
        authority.ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public void updateUser(UUID id, String email, String name, boolean activated) {
        userRepository.findOneById(id).ifPresent(user -> {
            user.setEmail(email);
            log.debug("Changed Information for User: {}", user);
        });
    }

    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<String> getAuthorities() {
        return roleRepository.findAll().stream().map(Role::getName).collect(Collectors.toList());
    }

    public void deleteUser(UUID userId) {
        userRepository.findOneById(userId).ifPresent(user -> {
            userRepository.deleteById(userId);
        });
    }
}
