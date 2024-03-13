package com.spame.api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spame.api.dtos.DoctorDTO;
import com.spame.api.dtos.EmployeeDTO;
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

  public void createEmployee(EmployeeDTO employee) {
    employeeRepository.save(new Employee(employee));
  }

  @Transactional
  public void saveDoctor(DoctorDTO doc) {
    try {
      Employee employee = new Employee(doc.employee());
      employeeRepository.save(employee);

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
