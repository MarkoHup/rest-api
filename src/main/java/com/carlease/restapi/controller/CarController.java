package com.carlease.restapi.controller;

import com.carlease.restapi.dto.CarDTO;
import com.carlease.restapi.service.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {

  @Autowired
  private CarService carService;

  @GetMapping
  public ResponseEntity<List<CarDTO>> getAllCars() {
    return ResponseEntity.ok(carService.getAllCars());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
    CarDTO carDTO = carService.getCarById(id);
    return ResponseEntity.ok(carDTO);
  }

  @PostMapping
  public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
    CarDTO createdCar = carService.createCar(carDTO);
    return ResponseEntity.ok(createdCar);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CarDTO> updateProduct(@PathVariable Long id,
      @RequestBody CarDTO carDTO) {
    CarDTO updatedCar = carService.updateCar(id, carDTO);
    return ResponseEntity.ok(updatedCar);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
    carService.deleteCar(id);
    return ResponseEntity.noContent().build();
  }
}