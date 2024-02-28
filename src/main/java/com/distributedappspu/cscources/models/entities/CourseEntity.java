package com.distributedappspu.cscources.models.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 300, nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private InstructorEntity instructor;

    @ManyToMany
    private List<StudentEntity> students;
}

