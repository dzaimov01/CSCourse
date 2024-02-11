package com.distributedappspu.cscources.models.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;

    @ManyToMany(mappedBy = "students")
    private List<CourseEntity> courses;
}

