package com.distributedappspu.cscources.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Long instructorId;
}

