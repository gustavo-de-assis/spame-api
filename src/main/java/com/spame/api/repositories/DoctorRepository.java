package com.spame.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spame.api.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
