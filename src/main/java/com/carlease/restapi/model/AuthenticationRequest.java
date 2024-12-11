package com.carlease.restapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Data
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
public class AuthenticationRequest {

  private String username;
  private String password;
}
