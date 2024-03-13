package com.spame.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spame.api.dtos.AddressDTO;
import com.spame.api.dtos.PatientDTO;
import com.spame.api.models.Address;
import com.spame.api.models.Patient;
import com.spame.api.repositories.AddressRepository;
import com.spame.api.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {
  final PatientRepository patientRepository;
  final AddressRepository addressRepository;

  PatientService(PatientRepository patientRepository, AddressRepository addressRepository) {
    this.patientRepository = patientRepository;
    this.addressRepository = addressRepository;
  }

  public List<Patient> findAll() {
    return patientRepository.findAll();
  }

  public Optional<Patient> findById(Long id) {
    return patientRepository.findById(id);
  }

  @Transactional
  public void createPatient(PatientDTO pat) {
    try {
      Address address = upsertAddress(pat.address());

      Patient patient = new Patient(pat, address);

      patientRepository.save(patient);

    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to parse data: " + e.getMessage());
      // TODO: handle exception
    }
  }

  private Address upsertAddress(AddressDTO addressDTO) {
    Address existingAddress = addressRepository.findByStreetAndHouseNumberAndCityAndState(
        addressDTO.street(), addressDTO.houseNumber(),
        addressDTO.city(), addressDTO.state());

    if (existingAddress != null) {
      return existingAddress;
    } else {
      Address newAddress = new Address(addressDTO);
      return addressRepository.save(newAddress);
    }
  }
}
