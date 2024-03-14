package com.spame.api.models;

import java.time.LocalDate;
import java.util.Date;

import com.spame.api.dtos.PatientDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Patient {
  public Patient() {

  }

  public Patient(PatientDTO data, Address address) {
    this.name = data.name();
    this.birthdate = LocalDate.parse(data.birthdate());
    this.gender = data.gender();
    this.cpf = data.cpf();
    this.rg = data.rg();
    this.mother = data.mother();
    this.father = data.father();
    this.email = data.email();
    this.phone = data.phone();
    this.address = address;

  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column()
  @Temporal(TemporalType.DATE)
  private LocalDate birthdate;

  @Column(length = 1, nullable = false)
  private String gender;

  @Column(length = 11, nullable = false, unique = true)
  private String cpf;

  @Column(nullable = true, unique = true)
  private String rg;

  @Column(length = 50, nullable = false)
  private String mother;

  @Column(length = 50, nullable = false)
  private String father;

  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @Column(length = 11, nullable = false)
  private String phone;

  @ManyToOne
  @JoinColumn(name = "addressId", referencedColumnName = "id")
  private Address address;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date updatedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
    updatedAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = new Date();
  }

}
