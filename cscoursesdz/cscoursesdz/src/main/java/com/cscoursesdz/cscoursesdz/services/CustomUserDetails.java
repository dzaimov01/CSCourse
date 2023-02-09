package com.cscoursesdz.cscoursesdz.services;

import com.cscoursesdz.cscoursesdz.common.AuthoritiesConstants;
import com.cscoursesdz.cscoursesdz.models.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public static CustomUserDetails create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.USER));

        return CustomUserDetails.builder()
                .id(user.getId())
                .name(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities).build();
    }

    public static CustomUserDetails create(User user, Map<String, Object> attributes) {
        CustomUserDetails customUserDetails = CustomUserDetails.create(user);
        customUserDetails.setAttributes(attributes);
        return customUserDetails;
    }
}
