package com.spame.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank String street,

        @NotBlank String houseNumber,

        String complement,

        @NotBlank String district,

        @NotBlank String city,

        @NotBlank @Pattern(regexp = "(A(C|L|P|M))|BA|(CE)|(DF)|(GO)|(ES)|(M(A|T|S|G))|(P(A|B|R|E|I))|(R(J|N|S|O|R))|(S(P|C|E))|(TO)") String state) {

}
