package com.carlease.restapi.controller;

import com.carlease.restapi.model.AuthenticationRequest;
import com.carlease.restapi.model.User;
import com.carlease.restapi.repository.UserRepository;
import com.carlease.restapi.service.CustomUserDetailsService;
import com.carlease.restapi.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return ResponseEntity.ok("User registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody AuthenticationRequest authenticationRequest)
      throws Exception {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
            authenticationRequest.getPassword())
    );

    final UserDetails userDetails = userDetailsService.loadUserByUsername(
        authenticationRequest.getUsername());

    return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
  }
}