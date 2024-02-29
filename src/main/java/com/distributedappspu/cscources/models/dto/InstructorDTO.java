package com.distributedappspu.cscources.models.dto;

import jakarta.validation.constraints.*;
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

    @NotNull(message = "Instructor's first name cannot be empty!")
    @NotBlank(message = "Instructor's first name cannot be empty!")
    @Size(max = 20, message = "Instructor's first name cannot be longer than 20 characters!")
    private String firstName;

    @NotNull(message = "Instructor's last name cannot be empty!")
    @NotBlank(message = "Instructor's last name cannot be empty!")
    @Size(max = 20, message = "Instructor's last name cannot be longer than 20 characters!")
    private String lastName;

    @NotNull(message = "Instructor's email cannot be empty!")
    @NotBlank(message = "Instructor's email cannot be empty!")
    @Email(message = "Instructor's email is not in a correct format!")
    @Size(max = 30, message = "Instructor's email cannot be longer than 30 characters!")
    private String email;

    @NotNull(message = "Instructor's username cannot be empty!")
    @NotBlank(message = "Instructor's username cannot be empty!")
    @Size(max = 20, message = "Instructor's username cannot be longer than 20 characters!")
    private String username;

    @NotNull(message = "Instructor's password cannot be empty!")
    @NotBlank(message = "Instructor's password cannot be empty!")
    @Size(max = 20, message = "Instructor's password cannot be longer than 20 characters!")
    private String password;

    @NotNull(message = "Instructor's date of birth cannot be empty!")
    @Past(message = "Instructor's date of birth cannot be in the present or future!")
    private Date dateOfBirth;

    @NotNull(message = "Instructor's hire date cannot be empty!")
    @PastOrPresent(message = "Instructor's hire date cannot be in the future!")
    private Date hireDate;

    private List<UUID> courseIds;
}

