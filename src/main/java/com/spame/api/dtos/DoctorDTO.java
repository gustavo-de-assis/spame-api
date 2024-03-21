package com.spame.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorDTO(
    @NotNull EmployeeDTO employee,
    @NotBlank String crm,
    @NotBlank String speciality) {
}
