package com.cscoursesdz.cscoursesdz.dto;

import com.cscoursesdz.cscoursesdz.models.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDTO {
    @Data
    public static class Create {
        private UUID id;
        private String email;
        private String password;
        private String userName;
        private Set<String> authorities;
        private String createdBy;
        private LocalDateTime createdDate;
        private String lastModifiedBy;
        private LocalDateTime lastModifiedDate;
    }

    @Data
    public static class Update {
        private UUID id;
        private String email;
        private Set<String> authorities;
    }

    @Data
    public static class Response {
        private UUID id;
        private String email;
        private Set<String> authorities;


        public void setAuthorities(Set<Role> authorities) {
            this.authorities = authorities.stream().map(Role::getName).collect(Collectors.toSet());
        }
    }

    @Data
    public static class Login {
        private String login;
        private String password;
    }
}
