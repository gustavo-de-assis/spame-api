package com.spame.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spame.api.models.Appointment;
import com.spame.api.repositories.AppointmentRepository;
import com.spame.api.repositories.DoctorRepository;
import com.spame.api.repositories.PatientRepository;

@Service
public class AppointmentService {
  final AppointmentRepository appointmentRepository;
  final PatientRepository patientRepository;
  final DoctorRepository doctorRepository;

  AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
      DoctorRepository doctorRepository) {
    this.appointmentRepository = appointmentRepository;
    this.patientRepository = patientRepository;
    this.doctorRepository = doctorRepository;
  }

  public Optional<Appointment> getAppointmentById(Long id) {
    return appointmentRepository.findById(id);
  }
}
