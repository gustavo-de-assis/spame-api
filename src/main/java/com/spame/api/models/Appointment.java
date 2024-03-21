package com.spame.api.models;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column()
  private LocalDateTime scheduleDate;

  @Column()
  private String diagnosis;

  @OneToOne
  @JoinColumn(name = "doctorId", referencedColumnName = "id")
  private Doctor doctor;

  @OneToOne
  @JoinColumn(name = "recepId", referencedColumnName = "id")
  private Employee employee;

  @OneToOne
  @JoinColumn(name = "patientId", referencedColumnName = "id")
  private Patient patient;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  }
}
