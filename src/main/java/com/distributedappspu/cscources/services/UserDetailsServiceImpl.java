package com.distributedappspu.cscources.services;

import com.distributedappspu.cscources.models.CustomUserDetails;
import com.distributedappspu.cscources.models.dto.AuthRequestDTO;
import com.distributedappspu.cscources.models.entities.UserInfo;
import com.distributedappspu.cscources.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("could not found user..!!");
        }
        return new CustomUserDetails(user);
    }

    public AuthRequestDTO registerNewUser(AuthRequestDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        UserInfo newUser = new UserInfo();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserInfo savedUser = userRepository.save(newUser);

        return new AuthRequestDTO(savedUser.getUsername(), savedUser.getPassword());
    }
}
