package com.cscoursesdz.cscoursesdz.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.cscoursesdz.cscoursesdz.common.AuthProvider;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(insertable=false, updatable=false, nullable=false)
    private UUID id;

    @Column(length=30, unique=true, nullable=false)
    private String email;

    @Column(length=30, unique=true, nullable=false)
    private String userName;

    @JsonIgnore
    @Column(length=60, nullable=true)
    private String password;

    private String imageUrl;

    @NonNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @JsonIgnore
    @ManyToMany
    @BatchSize(size=20)
    private Set<Role> authorities = new HashSet<>();

    @OneToMany(mappedBy="user")
    @JsonBackReference
    private Set<BlogPost> posts;

    @CreatedBy
    @Column(length=50, updatable=false)
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(length=50)
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
