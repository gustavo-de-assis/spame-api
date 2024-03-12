package com.spame.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorDTO(
        @NotNull Long employeeId,
        @NotBlank String crm,
        @NotBlank String speciality

) {

}
