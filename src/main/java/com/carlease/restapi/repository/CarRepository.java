package com.carlease.restapi.repository;

import com.carlease.restapi.model.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  List<Car> findByMakeAndModelAndVersion(String make, String model, String version);
}