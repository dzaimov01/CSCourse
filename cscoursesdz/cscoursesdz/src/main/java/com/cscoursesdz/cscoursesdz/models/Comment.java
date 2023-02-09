package com.cscoursesdz.cscoursesdz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable=false, updatable=false, nullable=false)
    private UUID id;

    @Column(name="body", columnDefinition="TEXT")
    private String body;

    @Column(name="created_date", nullable=false, updatable=false)
    @NonNull
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(name="last_modified_date")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="post_id", referencedColumnName="post_id", nullable=false)
    @NonNull
    private BlogPost post;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
    @NonNull
    private User user;
}
