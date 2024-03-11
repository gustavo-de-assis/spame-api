package com.spame.api.models;

import com.spame.api.dtos.EmployeeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  @Column()
  private String name;

  @Column()
  private String cpf;

  @Column()
  private String password;

  @Column()
  private String role;

}
