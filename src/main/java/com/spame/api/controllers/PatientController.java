package com.spame.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spame.api.dtos.PatientDTO;
import com.spame.api.models.Patient;
import com.spame.api.services.PatientService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
  final PatientService patientService;

  PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @GetMapping()
  public List<Patient> getPatients() {
    return patientService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Patient> getPatientById(@PathVariable Long id) {
    return patientService.findById(id);
  }

  @PostMapping()
  public void create(@RequestBody @Valid PatientDTO req) {
    patientService.createPatient(req);
  }

}
