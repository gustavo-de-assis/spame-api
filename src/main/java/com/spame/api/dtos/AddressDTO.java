package com.spame.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
    @NotBlank String street,

    @NotBlank String houseNumber,

    String complement,

    @NotBlank String district,

    @NotBlank String city,

    @NotBlank String state) {

}
