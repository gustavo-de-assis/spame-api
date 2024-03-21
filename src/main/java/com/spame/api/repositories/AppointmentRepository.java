package com.spame.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spame.api.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}