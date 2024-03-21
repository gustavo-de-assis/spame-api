package com.spame.api.models;

import com.spame.api.dtos.DoctorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Doctor {
  public Doctor() {
  }

  public Doctor(DoctorDTO data, Employee employee) {
    this.crm = data.crm();
    this.speciality = data.speciality();
    this.employee = employee;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @OneToOne
  @JoinColumn(name = "employeeId", referencedColumnName = "id")
  private Employee employee;

  @Column()
  private String crm;

  @Column()
  private String speciality;
}
