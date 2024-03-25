package com.spame.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spame.api.models.Appointment;
import com.spame.api.repositories.MedicalRecordRepository;

@Service
public class MedicalRecordService {

  final MedicalRecordRepository medicalRecordRepository;

  public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
    this.medicalRecordRepository = medicalRecordRepository;
  }

  public List<Appointment> getPatientMd(Long id) {
    return medicalRecordRepository.findAttendedAppointmentsByPatientId(id);
  }

}
