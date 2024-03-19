package com.spame.api.services;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spame.api.dtos.DoctorDTO;
import com.spame.api.dtos.EmployeeDTO;
import com.spame.api.enums.UserRole;
import com.spame.api.models.Doctor;
import com.spame.api.models.Employee;
import com.spame.api.repositories.DoctorRepository;
import com.spame.api.repositories.EmployeeRepository;

@Service
public class EmployeeService {
  final EmployeeRepository employeeRepository;
  final DoctorRepository doctorRepository;

  EmployeeService(EmployeeRepository employeeRepository, DoctorRepository doctorRepository) {
    this.employeeRepository = employeeRepository;
    this.doctorRepository = doctorRepository;
  }

  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  // public ResponseEntity createEmployee(EmployeeDTO employee) {
  public Employee createEmployee(EmployeeDTO employee) {
    if (this.employeeRepository.findByCpf(employee.cpf()) != null) {
      // return ResponseEntity.badRequest().build();
      throw new RuntimeException("Employee with this CPF already exists.");
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(employee.password());

    EmployeeDTO newEmployee = new EmployeeDTO(employee.name(), employee.cpf(), encryptedPassword, employee.role());

    return employeeRepository.save(new Employee(newEmployee));

    // return ResponseEntity.ok().build();
  }

  @Transactional
  public void saveDoctor(DoctorDTO doc) {
    EmployeeDTO employeeDTO = doc.employee();
    try {
      Employee employee = createEmployee(employeeDTO);
      Doctor doctor = new Doctor(doc, employee);

      doctorRepository.save(doctor);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to save doctor and employee: " + e.getMessage());
    }
  }

  public void deleteById(Long id) {
    employeeRepository.deleteById(id);
  }

}
