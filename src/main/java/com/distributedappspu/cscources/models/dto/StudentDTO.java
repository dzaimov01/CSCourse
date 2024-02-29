package com.distributedappspu.cscources.models.dto;

import jakarta.transaction.Transactional;
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
public class StudentDTO {

    private UUID id;

    @NotNull(message = "Student's first name cannot be empty!")
    @NotBlank(message = "Student's first name cannot be empty!")
    @Size(max = 20, message = "Student's first name cannot be longer than 20 characters!")
    private String firstName;

    @NotNull(message = "Student's last name cannot be empty!")
    @NotBlank(message = "Student's last name cannot be empty!")
    @Size(max = 20, message = "Student's last name cannot be longer than 20 characters!")
    private String lastName;

    @NotNull(message = "Student's email cannot be empty!")
    @NotBlank(message = "Student's email cannot be empty!")
    @Email(message = "Student's email is not in a correct format!")
    @Size(max = 30, message = "Student's email cannot be longer than 20 characters!")
    private String email;

    @NotNull(message = "Student's username cannot be empty!")
    @NotBlank(message = "Student's username cannot be empty!")
    @Size(max = 20, message = "Student's username cannot be longer than 20 characters!")
    private String username;

    @NotNull(message = "Student's password cannot be empty!")
    @NotBlank(message = "Student's password cannot be empty!")
    @Size(max = 20, message = "Student's password cannot be longer than 20 characters!")
    private String password;

    @NotNull(message = "Student's date of birth cannot be empty!")
    @Past(message = "Student's date of birth cannot be in the present or future!")
    private Date dateOfBirth;

    @NotNull(message = "Student's enrollment date cannot be empty!")
    @FutureOrPresent(message = "Student's enrollment date cannot be in the past!")
    private Date enrollmentDate;

    private List<UUID> courseIds;
}
