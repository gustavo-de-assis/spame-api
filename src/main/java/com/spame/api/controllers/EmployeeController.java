package com.spame.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spame.api.dtos.DoctorDTO;
import com.spame.api.dtos.EmployeeDTO;
import com.spame.api.enums.UserRole;
import com.spame.api.models.Employee;
import com.spame.api.repositories.EmployeeRepository;
import com.spame.api.services.AuthorizationService;
import com.spame.api.services.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  final AuthorizationService authorizationService;
  final EmployeeService employeeService;

  EmployeeController(EmployeeService employeeService, AuthorizationService authorizationService) {
    this.employeeService = employeeService;
    this.authorizationService = authorizationService;
  }

  @Autowired
  private EmployeeRepository repository;

  @GetMapping
  public List<Employee> getAllEmployees() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Employee> getEmployeeById(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping
  public void create(@RequestBody @Valid EmployeeDTO req) {
    employeeService.createEmployee(req);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @RequestBody @Valid EmployeeDTO req) {
    repository.findById(id).map(employee -> {
      employee.setName(req.name());
      employee.setPassword(req.password());
      employee.setCpf(req.cpf());
      // employee.setRole(UserRole.req.role());
      return repository.save(employee);
    });
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }

  @PostMapping("/doctor")
  public void createDoctor(@RequestBody @Valid DoctorDTO req) {
    employeeService.saveDoctor(req);
  }

}
