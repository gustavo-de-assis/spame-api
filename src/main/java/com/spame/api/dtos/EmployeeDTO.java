package com.spame.api.dtos;

import com.spame.api.enums.UserRole;

import jakarta.validation.constraints.NotBlank;

public record EmployeeDTO(
        @NotBlank String name,
        @NotBlank String cpf,
        @NotBlank String password,
        @NotBlank UserRole role) {

}
