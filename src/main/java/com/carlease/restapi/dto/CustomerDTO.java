package com.carlease.restapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Data
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
// Lombok annotation to generate an all-args constructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

  private Long id;
  @NotBlank(message = "Name is required!")
  private String Name;
  private String Street;
  private String HouseNumber;
  private String ZipCode;
  private String Place;
  @Email(message = "Email is not in a valid format!")
  private String EmailAddress;
  private String PhoneNumber;
}
