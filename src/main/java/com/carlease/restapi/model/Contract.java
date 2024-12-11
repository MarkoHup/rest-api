package com.carlease.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotates the class as a JPA entity, allowing it to be mapped to a database table
@Entity
// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Data
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
@Table(name = "contracts")
public class Contract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "car_id")
  private Car car;
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
  private LocalDate startDate;
  private LocalDate endDate;
  private Integer mileage;
  private Double interestRate;

  public Contract(Car car, Customer customer, LocalDate startDate, LocalDate endDate,
      Integer mileage, Double interestRate) {
    this.car = car;
    this.customer = customer;
    this.startDate = startDate;
    this.endDate = endDate;
    this.mileage = mileage;
    this.interestRate = interestRate;
  }

}