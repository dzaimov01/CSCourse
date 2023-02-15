package com.cscoursesdz.cscoursesdz.controllers;

import com.cscoursesdz.cscoursesdz.autentication.CurrentUser;
import com.cscoursesdz.cscoursesdz.autentication.CustomUserDetails;
import com.cscoursesdz.cscoursesdz.autentication.JwtAuthResponse;
import com.cscoursesdz.cscoursesdz.autentication.JwtUtil;
import com.cscoursesdz.cscoursesdz.common.AuthProvider;
import com.cscoursesdz.cscoursesdz.exceptions.ApiException;
import com.cscoursesdz.cscoursesdz.models.User;
import com.cscoursesdz.cscoursesdz.models.login.LoginRequest;
import com.cscoursesdz.cscoursesdz.models.registration.SignUpRequest;
import com.cscoursesdz.cscoursesdz.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @NonNull
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private final String AUTHORIZATION_HEADER = "Authorization";

    @PostMapping(value = "/authenticate")
    public ResponseEntity createAuthenticationToken(@RequestBody LoginRequest request
            , @RequestParam(value = "rememberMe", defaultValue = "false", required = false) boolean rememberMe
            , HttpServletResponse response) {
        log.debug("REST request to authenticate : {}", request.getEmail());
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = "Bearer " + jwtUtil.createToken(authentication, rememberMe);
            response.addHeader(AUTHORIZATION_HEADER, jwt);
            return ResponseEntity.ok(new JwtAuthResponse(jwt));
        } catch (AuthenticationException ae) {
            log.trace("Authentication exception trace: {0}", ae);
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",
                    ae.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser CustomUserDetails CustomUserDetails) {
        log.debug("REST request to get user : {}", CustomUserDetails.getEmail());
        return userRepository.findById(CustomUserDetails.getId())
                .orElseThrow(() -> new ApiException("User", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.debug("REST request to signup : {}", signUpRequest.getEmail());
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email address already in use.");
        }

        User user = new User();
        user.setUserName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userRepository.save(user);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
