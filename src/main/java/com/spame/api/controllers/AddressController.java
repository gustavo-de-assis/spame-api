package com.spame.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spame.api.models.Address;
import com.spame.api.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping
public class AddressController {

  @Autowired
  private AddressRepository repository;

  @GetMapping("/api/addresses")
  public List<Address> getAllAddresses() {
    return repository.findAll();
  }

}
