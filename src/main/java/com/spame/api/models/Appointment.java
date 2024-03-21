package com.spame.api.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spame.api.dtos.AppointmentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Appointment {

  public Appointment() {

  }

  public Appointment(AppointmentDTO data, Employee recep, Doctor doctor, Patient patient) {
    this.diagnosis = data.diagnosis();
    this.scheduleDate = data.scheduleDate();
    this.employee = recep;
    this.doctor = doctor;
    this.patient = patient;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column()
  private LocalDateTime scheduleDate;

  @Column()
  private String diagnosis;

  @Column(columnDefinition = "boolean default false")
  private Boolean attended;

  @PreUpdate
  protected void onUpdate() {
    if (this.diagnosis != null && !this.diagnosis.isEmpty()) {
      this.attended = true;
    }
  }

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

  @JsonIgnore
  @OneToMany(mappedBy = "appointment")
  private List<MedicalRecord> medicalRecords;
}
