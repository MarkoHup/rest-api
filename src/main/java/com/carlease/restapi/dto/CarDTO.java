package com.carlease.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Data
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
// Lombok annotation to generate an all-args constructor
@AllArgsConstructor
public class CarDTO {

  private Long id;
  private String make;
  private String model;
  private String version;
  private Integer numberOfDoors;
  private Double co2Emission;
  private Double grossPrice;
  private Double nettPrice;
}
