package com.cscoursesdz.cscoursesdz.autentication;

import com.cscoursesdz.cscoursesdz.common.ApplicationProperties;
import com.cscoursesdz.cscoursesdz.common.AuthProvider;
import com.cscoursesdz.cscoursesdz.models.Role;
import com.cscoursesdz.cscoursesdz.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import io.jsonwebtoken.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtUtil {
    private static final String AUTHORITIES_KEY = "authorities";

    private String secretKey;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    private ApplicationProperties applicationProperties;

    public JwtUtil(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.secretKey =
                applicationProperties.getSecurity().getJwt().getSecret();

        this.tokenValidityInMilliseconds =
                1000 * applicationProperties.getSecurity().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
                1000 * applicationProperties.getSecurity().getJwt().getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(Authentication authentication) {
        return createToken(authentication, false);
    }

    public String createToken(Authentication authentication, Boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (Boolean.TRUE.equals(rememberMe)) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setId(customUserDetails.getId().toString())
                .setSubject(customUserDetails.getEmail())
                .setIssuedAt(new Date())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public String createAdminToken() {
        return Jwts.builder()
                .setSubject("admin")
                .claim(AUTHORITIES_KEY, "ROLE_ADMIN")
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(new Date((new Date()).getTime() + 1000 * 3600 * 24 * 365))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(
                User.builder()
                        .provider(AuthProvider.valueOf(claims.getSubject()))
                        .authorities(authorities).build(), token, authorities);
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}
