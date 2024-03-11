package com.spame.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spame.api.dtos.AddressDTO;
import com.spame.api.models.Address;
import com.spame.api.repositories.AddressRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

  @Autowired
  private AddressRepository repository;

  @GetMapping
  public List<Address> getAllAddresses() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Address> findAddressById(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping
  public void create(@RequestBody @Valid AddressDTO req) {
    repository.save(new Address(req));
  }

  /*
   * @DeleteMapping("/{id}")
   * public void delete(@PathVariable Long id) {
   * repository.deleteById(id);
   * }
   */

}
