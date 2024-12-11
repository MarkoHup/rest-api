package com.carlease.restapi.dto;

import static java.time.temporal.ChronoUnit.MONTHS;

import com.carlease.restapi.model.Car;
import com.carlease.restapi.model.Customer;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Data
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
// Lombok annotation to generate an all-args constructor
@AllArgsConstructor
public class ContractDTO {

  private Long id;
  private Car car;
  private Customer customer;
  private LocalDate startDate;
  private LocalDate endDate;
  private Integer mileage;
  private Double interestRate;

  private Double getDuration() {
    return (double) MONTHS.between(startDate, endDate);
  }

  public Double getLeaseRate() {
    return ((getMileage() / 12.0) * getDuration()) / car.getNettPrice() + (
        ((getInterestRate() / 100) * car.getNettPrice()) / 12);
  }
}
