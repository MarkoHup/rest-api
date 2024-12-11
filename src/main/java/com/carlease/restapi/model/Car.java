package com.carlease.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotates the class as a JPA entity, allowing it to be mapped to a database table
@Entity
// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Data
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String make;
  private String model;
  private String version;
  private Integer numberOfDoors;
  private Double co2Emission;
  private Double grossPrice;
  private Double nettPrice;

  public Car(String make, String model, String version, Integer numberOfDoors,
      Double co2Emission, Double grossPrice, Double nettPrice) {
    this.make = make;
    this.model = model;
    this.numberOfDoors = numberOfDoors;
    this.version = version;
    this.co2Emission = co2Emission;
    this.grossPrice = grossPrice;
    this.nettPrice = nettPrice;
  }

}