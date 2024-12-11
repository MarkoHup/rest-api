package com.carlease.restapi.service;

import com.carlease.restapi.dto.CustomerDTO;
import com.carlease.restapi.model.Customer;
import com.carlease.restapi.repository.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  private CustomerDTO mapToDTO(Customer customer) {
    return new CustomerDTO(customer.getId(), customer.getName(), customer.getStreet(),
        customer.getHouseNumber(), customer.getZipCode(), customer.getPlace(),
        customer.getEmailAddress(), customer.getPhoneNumber());
  }

  private Customer mapToEntity(CustomerDTO customerDTO) {
    return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getStreet(),
        customerDTO.getHouseNumber(), customerDTO.getZipCode(), customerDTO.getPlace(),
        customerDTO.getEmailAddress(), customerDTO.getPhoneNumber());
  }

  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll().stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  public CustomerDTO getCustomerById(Long id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    return mapToDTO(customer);
  }

  public CustomerDTO createCustomer(CustomerDTO customerDTO) {
    Customer customer = mapToEntity(customerDTO);
    Customer savedCustomer = customerRepository.save(customer);
    return mapToDTO(savedCustomer);
  }

  public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
    Customer existingCustomer = customerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    existingCustomer.setName(customerDTO.getName());
    existingCustomer.setStreet(customerDTO.getStreet());
    existingCustomer.setHouseNumber(customerDTO.getHouseNumber());
    existingCustomer.setZipCode(customerDTO.getZipCode());
    existingCustomer.setPlace(customerDTO.getPlace());
    existingCustomer.setEmailAddress(customerDTO.getEmailAddress());
    existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
    Customer updatedCustomer = customerRepository.save(existingCustomer);
    return mapToDTO(updatedCustomer);
  }

  public void deleteCustomer(Long id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    customerRepository.delete(customer);
  }
}