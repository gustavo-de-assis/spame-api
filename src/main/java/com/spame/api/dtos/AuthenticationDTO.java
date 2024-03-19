package com.spame.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
    @NotBlank String cpf,
    @NotBlank String password) {

}
