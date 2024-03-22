package com.spame.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spame.api.dtos.AppointmentDTO;
import com.spame.api.models.Appointment;
import com.spame.api.models.Doctor;
import com.spame.api.models.Employee;
import com.spame.api.models.MedicalRecord;
import com.spame.api.models.Patient;
import com.spame.api.repositories.AppointmentRepository;
import com.spame.api.repositories.DoctorRepository;
import com.spame.api.repositories.EmployeeRepository;
import com.spame.api.repositories.MedicalRecordRepository;
import com.spame.api.repositories.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AppointmentService {
  final AppointmentRepository appointmentRepository;
  final PatientRepository patientRepository;
  final DoctorRepository doctorRepository;
  final MedicalRecordRepository medicalRecordRepository;
  final EmployeeRepository employeeRepository;

  AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
      DoctorRepository doctorRepository, MedicalRecordRepository medicalRecordRepository,
      EmployeeRepository employeeRepository) {
    this.appointmentRepository = appointmentRepository;
    this.patientRepository = patientRepository;
    this.doctorRepository = doctorRepository;
    this.medicalRecordRepository = medicalRecordRepository;
    this.employeeRepository = employeeRepository;
  }

  public Optional<Appointment> getAppointmentById(Long id) {
    return appointmentRepository.findById(id);
  }

  @Transactional
  public void saveAppointmentAndMedicalRecord(AppointmentDTO appointmentDTO, Long employeeId) {
    Optional<Employee> recepOpt = employeeRepository.findById(employeeId);
    Optional<Doctor> doctorOpt = doctorRepository.findById(appointmentDTO.doctorId());
    Optional<Patient> patientOpt = patientRepository.findById(appointmentDTO.patientId());

    if (recepOpt.isEmpty() || doctorOpt.isEmpty() || patientOpt.isEmpty()) {
      throw new EntityNotFoundException("Employee, doctor, or patient not found!");
    }

    try {
      Employee recep = recepOpt.get();
      Doctor doctor = doctorOpt.get();
      Patient patient = patientOpt.get();

      Appointment appointment = new Appointment(appointmentDTO, recep, doctor, patient);
      appointmentRepository.save(appointment);

      MedicalRecord md = new MedicalRecord(patient, appointment);
      medicalRecordRepository.save(md);

    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to save appointment and medical record: \n" + e.getMessage());
    }
  }
}
