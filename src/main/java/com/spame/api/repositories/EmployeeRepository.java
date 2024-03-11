package com.spame.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spame.api.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
