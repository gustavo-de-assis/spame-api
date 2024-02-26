package com.spame.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spame.api.models.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {

}
