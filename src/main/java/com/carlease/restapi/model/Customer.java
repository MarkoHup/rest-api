package com.carlease.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotates the class as a JPA entity, allowing it to be mapped to a database table
@Entity
// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Data
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
// Lombok annotation to generate an all-args constructor
@AllArgsConstructor
// Lombok annotation to generate a builder pattern for creating instances
@Builder
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Name is required!")
  private String name;
  private String street;
  private String houseNumber;
  private String zipCode;
  private String place;
  @Email(message = "Email is not in a valid format!")
  private String emailAddress;
  private String phoneNumber;

  public Customer(String name, String street, String houseNumber, String zipCode, String place,
      String emailAddress, String phoneNumber) {
    this.name = name;
    this.street = street;
    this.houseNumber = houseNumber;
    this.zipCode = zipCode;
    this.place = place;
    this.emailAddress = emailAddress;
    this.phoneNumber = phoneNumber;
  }
}