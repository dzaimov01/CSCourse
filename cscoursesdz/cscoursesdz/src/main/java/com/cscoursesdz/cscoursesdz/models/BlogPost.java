package com.cscoursesdz.cscoursesdz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "body", columnDefinition = "TEXT")
    private String body;
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id", referencedColumnName="user_id", nullable=false)
    @NonNull
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JoinColumn(name="comments")
    private Set<Comment> comments;
    @Column(name = "created_by", length = 50, updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();
}
