package com.distributedappspu.cscources.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO {

    private UUID id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private Date dateOfBirth;

    @NotNull
    @NotBlank
    private Date hireDate;

    private List<UUID> courseIds;
}

