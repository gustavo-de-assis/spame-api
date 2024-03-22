package com.spame.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.spame.api.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  UserDetails findByCpf(String cpf);

  @Query("SELECT e.id FROM Employee e WHERE e.cpf = :cpf")
  Long findIdByCpf(@Param("cpf") String cpf);

}
