package com.spame.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spame.api.dtos.AppointmentDTO;
import com.spame.api.models.Appointment;
import com.spame.api.repositories.EmployeeRepository;
import com.spame.api.services.AppointmentService;
import com.spame.api.services.TokenService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

  final AppointmentService appointmentService;
  final TokenService tokenService;

  @Autowired
  EmployeeRepository employeeRepository;

  AppointmentController(AppointmentService appointmentService, TokenService tokenService) {
    this.appointmentService = appointmentService;
    this.tokenService = tokenService;
  }

  @GetMapping("/{id}")
  public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
    return appointmentService.getAppointmentById(id);
  }

  @PostMapping("/{id}")
  public void saveAppointmentAndMd(@RequestHeader("Authorization") String authorizationHeader,
      @RequestBody AppointmentDTO req, @PathVariable Long id) {
    try {
      String token = authorizationHeader.substring(7);
      String cpf = tokenService.validateToken(token);
      Long employeeId = employeeRepository.findIdByCpf(cpf);
      AppointmentDTO appointment = new AppointmentDTO(req.scheduleDate(), req.diagnosis(), req.doctorId(), id);
      appointmentService.saveAppointmentAndMedicalRecord(appointment, employeeId);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
