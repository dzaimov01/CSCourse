package com.distributedappspu.cscources.models.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private InstructorEntity instructor;
}

