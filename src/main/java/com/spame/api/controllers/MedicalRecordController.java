package com.spame.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spame.api.models.Appointment;
import com.spame.api.services.MedicalRecordService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/mdRecord")
public class MedicalRecordController {

  final MedicalRecordService medicalRecordService;

  MedicalRecordController(MedicalRecordService medicalRecordService) {
    this.medicalRecordService = medicalRecordService;
  }

  @GetMapping("/{id}")
  public List<Appointment> medicalRecord(@PathVariable Long id) {
    return medicalRecordService.getPatientMd(id);
  }

}
