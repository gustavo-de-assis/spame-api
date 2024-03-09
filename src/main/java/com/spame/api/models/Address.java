package com.spame.api.models;

import com.spame.api.dtos.AddressDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {

  public Address() {
  }

  public Address(AddressDTO data) {
    this.street = data.street();
    this.houseNumber = data.houseNumber();
    this.complement = data.complement();
    this.district = data.district();
    this.city = data.city();
    this.state = data.state();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(length = 50, nullable = false)
  private String street;

  @Column(length = 50, nullable = false)
  private String houseNumber;

  @Column(length = 50)
  private String complement;

  @Column(length = 50, nullable = false)
  private String district;

  @Column(length = 50, nullable = false)
  private String city;

  @Column(length = 2, nullable = false)
  private String state;
}
