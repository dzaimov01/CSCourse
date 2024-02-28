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
public class JwtResponseDTO {

    @NotNull(message = "Token cannot be empty!")
    @NotBlank(message = "Token cannot be empty!")
    private String accessToken;

}
