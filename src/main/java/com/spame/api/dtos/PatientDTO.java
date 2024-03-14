package com.spame.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientDTO(
    @NotBlank String name,
    @NotBlank String birthdate,
    @NotBlank String gender,
    @NotBlank String cpf,
    @NotNull String rg,
    @NotBlank String mother,
    @NotBlank String father,
    @NotBlank String email,
    @NotBlank String phone,
    @NotNull AddressDTO address

) {

}