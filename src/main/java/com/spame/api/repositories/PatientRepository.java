package com.spame.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spame.api.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}