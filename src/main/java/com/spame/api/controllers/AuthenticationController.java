package com.spame.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spame.api.dtos.AuthenticationDTO;
import com.spame.api.dtos.LoginResponseDTO;
import com.spame.api.models.Employee;
import com.spame.api.services.TokenService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDTO req) {
    var userNamePassword = new UsernamePasswordAuthenticationToken(req.cpf(), req.password());
    var auth = this.authenticationManager.authenticate(userNamePassword);

    var token = tokenService.generateToken((Employee) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

}
