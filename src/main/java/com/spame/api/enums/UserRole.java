package com.spame.api.enums;

public enum UserRole {

  ADMIN("admin"),

  RECEP("recep"),

  DOCTOR("doctor");

  private String role;

  UserRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

}
