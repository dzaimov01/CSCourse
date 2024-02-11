package com.distributedappspu.cscources.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private Date enrollmentDate;
    private List<Long> courseIds;
}
