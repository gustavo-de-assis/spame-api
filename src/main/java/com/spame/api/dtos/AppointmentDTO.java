package com.spame.api.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record AppointmentDTO(
    @NotBlank LocalDateTime scheduleDate,
    String diagnosis,
    @NotBlank Long doctorId,
    @NotBlank Long patientId) {

}
