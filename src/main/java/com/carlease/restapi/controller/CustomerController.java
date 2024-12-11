package com.carlease.restapi.controller;

import com.carlease.restapi.dto.CustomerDTO;
import com.carlease.restapi.service.CustomerService;
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
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping
  public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
    return ResponseEntity.ok(customerService.getAllCustomers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
    CustomerDTO customerDTO = customerService.getCustomerById(id);
    return ResponseEntity.ok(customerDTO);
  }

  @PostMapping
  public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
    CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
    return ResponseEntity.ok(createdCustomer);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDTO> updateProduct(@PathVariable Long id,
      @RequestBody CustomerDTO customerDTO) {
    CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
    return ResponseEntity.ok(updatedCustomer);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return ResponseEntity.noContent().build();
  }
}