package com.spame.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spame.api.models.Appointment;
import com.spame.api.models.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
  @Query("SELECT a FROM Appointment a JOIN a.medicalRecords md WHERE md.patient.id = :patientId AND a.attended = true")
  List<Appointment> findAttendedAppointmentsByPatientId(@Param("patientId") Long patientId);
}