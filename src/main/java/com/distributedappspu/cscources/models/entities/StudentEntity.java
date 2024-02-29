package com.distributedappspu.cscources.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String firstName;

    @Column(length = 20, nullable = false)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @Column(length = 30, nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date enrollmentDate;

    @ManyToMany(mappedBy = "students")
    private List<CourseEntity> courses;
}

