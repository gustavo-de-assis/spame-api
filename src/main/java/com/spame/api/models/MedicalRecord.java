package com.spame.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class MedicalRecord {

  public MedicalRecord(Patient patient, Appointment appointment) {
    this.patient = patient;
    this.appointment = appointment;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "patientId", referencedColumnName = "id")
  private Patient patient;

  @ManyToOne
  @JoinColumn(name = "appointmentId", referencedColumnName = "id")
  private Appointment appointment;

}
