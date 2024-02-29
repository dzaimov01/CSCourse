package com.distributedappspu.cscources.controllers;

import com.distributedappspu.cscources.config.JwtService;
import com.distributedappspu.cscources.models.dto.AuthRequestDTO;
import com.distributedappspu.cscources.models.dto.JwtResponseDTO;
import com.distributedappspu.cscources.services.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<JwtResponseDTO> authenticateAndGetToken(@Valid @RequestBody AuthRequestDTO authRequestDTO){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
            );

            if(authentication.isAuthenticated()){
                JwtResponseDTO jwtResponse = JwtResponseDTO.builder()
                        .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                        .build();
                return ResponseEntity.ok(jwtResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtResponseDTO("Authentication failed: " + e.getMessage()));
        }
    }

    @PostMapping("/api/v1//student/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRequestDTO userDTO) {
        AuthRequestDTO registeredUser = userDetailsService.registerNewUser(userDTO, "Student");
        if(registeredUser != null) {
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User registration failed", HttpStatus.BAD_REQUEST);
        }
    }
}
