package com.distributedappspu.cscources.models.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private UUID id;

    @NotNull(message = "Course name cannot be empty!")
    @NotBlank(message = "Course name cannot be empty!")
    @Size(max = 20, message = "Course name cannot be longer than 20 characters!")
    private String name;

    @NotNull(message = "Course description cannot be empty!")
    @NotBlank(message = "Course description cannot be empty!")
    @Size(max = 300, message = "Course description cannot be longer than 300 characters!")
    private String description;

    @NotNull(message = "Start date for course cannot be empty!")
    @NotBlank(message = "Start date for course cannot be empty!")
    @FutureOrPresent(message = "Date cannot be in the past!")
    private Date startDate;

    @NotNull(message = "End date for course cannot be empty!")
    @NotBlank(message = "End date for course cannot be empty!")
    @FutureOrPresent(message = "Date cannot be in the past!")
    private Date endDate;

    @NotNull(message = "Instructor cannot be empty!")
    @NotBlank(message = "Instructor cannot be empty!")
    private UUID instructorId;
}

