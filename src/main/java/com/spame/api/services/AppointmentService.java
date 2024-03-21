package com.spame.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spame.api.models.Appointment;
import com.spame.api.repositories.AppointmentRepository;

@Service
public class AppointmentService {
  final AppointmentRepository appointmentRepository;

  AppointmentService(AppointmentRepository appointmentRepository) {
    this.appointmentRepository = appointmentRepository;
  }

  public Optional<Appointment> getAppointmentById(Long id) {
    return appointmentRepository.findById(id);
  }
}
