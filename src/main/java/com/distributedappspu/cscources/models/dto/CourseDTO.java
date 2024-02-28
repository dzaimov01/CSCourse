package com.distributedappspu.cscources.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private UUID id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private Date startDate;

    @NotNull
    @NotBlank
    private Date endDate;

    @NotNull
    @NotBlank
    private UUID instructorId;
}

