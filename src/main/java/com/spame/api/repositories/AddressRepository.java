package com.spame.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spame.api.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
