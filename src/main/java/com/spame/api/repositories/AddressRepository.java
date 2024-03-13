package com.spame.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spame.api.models.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public interface AddressRepository extends JpaRepository<Address, Long> {

  Address findByStreetAndHouseNumberAndCityAndState(@NotBlank String street, @NotBlank String houseNumber,
      @NotBlank String city,
      @NotBlank @Pattern(regexp = "(A(C|L|P|M))|BA|(CE)|(DF)|(GO)|(ES)|(M(A|T|S|G))|(P(A|B|R|E|I))|(R(J|N|S|O|R))|(S(P|C|E))|(TO)") String state);

}
