package com.spame.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spame.api.models.Appointment;
import com.spame.api.services.AppointmentService;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

  final AppointmentService appointmentService;

  AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @GetMapping("/{id}")
  public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
    return appointmentService.getAppointmentById(id);
  }

}
