package com.spame.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmployeeDTO(
    @NotBlank String name,
    @NotBlank String cpf,
    @NotBlank String password,
    @NotBlank String role) {

}
