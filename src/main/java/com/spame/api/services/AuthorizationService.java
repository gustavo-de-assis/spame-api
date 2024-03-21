package com.spame.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spame.api.repositories.EmployeeRepository;

@Service
public class AuthorizationService implements UserDetailsService {

  @Autowired
  EmployeeRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByCpf(username);
  }

}
