package com.carlease.restapi.service;

import com.carlease.restapi.dto.CarDTO;
import com.carlease.restapi.model.Car;
import com.carlease.restapi.repository.CarRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  private final CarRepository carRepository;

  @Autowired
  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  private CarDTO mapToDTO(Car car) {
    return new CarDTO(car.getId(), car.getMake(), car.getModel(), car.getVersion(),
        car.getNumberOfDoors(), car.getCo2Emission(), car.getGrossPrice(), car.getNettPrice());
  }

  private Car mapToEntity(CarDTO carDTO) {
    return new Car(carDTO.getMake(), carDTO.getModel(), carDTO.getVersion(),
        carDTO.getNumberOfDoors(), carDTO.getCo2Emission(), carDTO.getGrossPrice(),
        carDTO.getNettPrice());
  }

  public List<CarDTO> getAllCars() {
    return carRepository.findAll().stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  public CarDTO getCarById(Long id) {
    Car car = carRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Car not found"));
    return mapToDTO(car);
  }

  public CarDTO createCar(CarDTO carDTO) {
    Car car = mapToEntity(carDTO);
    if (!carRepository.findByMakeAndModelAndVersion(car.getMake(), car.getModel(), car.getVersion())
        .isEmpty()) {
      throw new RuntimeException("Car already exists!");
    }
    Car savedCar = carRepository.save(car);
    return mapToDTO(savedCar);
  }

  public CarDTO updateCar(Long id, CarDTO carDTO) {
    Car existingCar = carRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Car not found"));
    existingCar.setMake(carDTO.getMake());
    existingCar.setModel(carDTO.getModel());
    existingCar.setVersion(carDTO.getVersion());
    existingCar.setNumberOfDoors(carDTO.getNumberOfDoors());
    existingCar.setCo2Emission(carDTO.getCo2Emission());
    existingCar.setGrossPrice(carDTO.getGrossPrice());
    existingCar.setNettPrice(carDTO.getNettPrice());
    Car updatedCar = carRepository.save(existingCar);
    return mapToDTO(updatedCar);
  }

  public void deleteCar(Long id) {
    Car car = carRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Car not found"));
    carRepository.delete(car);
  }
}