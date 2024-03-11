package com.spame.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spame.api.dtos.EmployeeDTO;
import com.spame.api.models.Employee;
import com.spame.api.repositories.EmployeeRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  private EmployeeRepository repository;

  @GetMapping
  public List<Employee> getAllEmployees() {
    return repository.findAll();
  }

  @PostMapping
  public void create(@RequestBody @Valid EmployeeDTO req) {

    repository.save(new Employee(req));
  }

}
