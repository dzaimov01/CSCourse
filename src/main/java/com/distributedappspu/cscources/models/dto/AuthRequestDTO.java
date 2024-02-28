package com.distributedappspu.cscources.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDTO {

    @NotNull(message = "Username cannot be blank!")
    @NotBlank(message = "Username cannot be blank!")
    private String username;

    @NotNull(message = "Password cannot be empty!")
    @NotBlank(message = "Password cannot be empty!")
    private String password;
}
