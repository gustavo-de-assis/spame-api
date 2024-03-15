package com.spame.api.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spame.api.dtos.EmployeeDTO;
import com.spame.api.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Employee implements UserDetails {
  public Employee() {

  }

  public Employee(EmployeeDTO data) {
    this.name = data.name();
    this.cpf = data.cpf();
    this.password = data.password();
    this.role = data.role();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 11, nullable = false)
  private String cpf;

  @Column(length = 50, nullable = false)
  private String password;

  @Column(length = 15, nullable = false)
  private UserRole role;

  @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
  private Doctor doctor;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == UserRole.ADMIN) {
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_RECEP"));
    } else if (this.role == UserRole.RECEP) {
      return List.of(new SimpleGrantedAuthority("ROLE_RECEP"));
    }
    return List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"));
  }

  @Override
  public String getUsername() {
    return cpf;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
