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
import jakarta.validation.Valid;

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

  @SuppressWarnings("null")
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

  public void updatePatient(Long id, @Valid PatientDTO req) {
    Optional<Patient> patient = patientRepository.findById(id);

    if (!patient.isPresent()) {
      throw new RuntimeException("Patient not Found!");
    } else {
      patientRepository.findById(id).map(pat -> {
        // pat.setBirthdate(req.birthdate());
        pat.setCpf(req.cpf());
        pat.setEmail(req.email());
        pat.setFather(req.father());
        pat.setMother(req.mother());
        pat.setPhone(req.phone());
        pat.setName(req.name());
        pat.setRg(req.rg());
        // pat.setAddress(req.address());
        return patientRepository.save(pat);
      });
    }

  }
}
