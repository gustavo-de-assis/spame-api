package com.spame.api.models;

import com.spame.api.dtos.EmployeeDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Employee {
  public Employee() {

  }

  public Employee(EmployeeDTO data) {
    this.name = data.name();
    this.cpf = data.cpf();
    this.password = data.password();
    this.role = data.role();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 11, nullable = false)
  private String cpf;

  @Column(length = 50, nullable = false)
  private String password;

  @Column(length = 15, nullable = false)
  private String role;

  @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
  private Doctor doctor;
}
