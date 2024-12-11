package com.carlease.restapi.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.carlease.restapi.model.Customer;
import com.carlease.restapi.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  @Transactional
  @DisplayName("Test 1:Save Customer Test")
  @Order(1)
  @Rollback(value = false)
  public void testSaveCustomer() {
    // Define test data
    String Name = "John Doe";
    String Street = "123 Main St";
    String HouseNumber = "456";
    String ZipCode = "12345";
    String Place = "City";
    String EmailAddress = "John@Doe.com";
    String PhoneNumber = "123-456-7890";

    // Create a Customer object with the test data
    Customer customer = Customer.builder().name(Name).street(Street).houseNumber(HouseNumber)
        .zipCode(ZipCode).place(Place).emailAddress(EmailAddress).phoneNumber(PhoneNumber).build();

    // Save the customer to the database
    Customer savedCustomer = customerRepository.save(customer);

    // Assert that the retrieved customer is not null
    assertNotNull(savedCustomer);

    // Assert that the retrieved customer id is not null
    assertNotNull(savedCustomer.getId());

    // Assert that the retrieved customer's name matches the expected name
    assertEquals(Name, savedCustomer.getName());

    // Assert that the retrieved customer's email matches the expected email
    assertEquals(EmailAddress, savedCustomer.getEmailAddress());
  }

  @Test
  @Order(2)
  public void testGetCustomer() {
    // Define test data
    Long Id = 1L;

    // Assert that the retrieved customer id is not null
    assertNotNull(Id);

    // Delete the customer from the database
    Customer customer = customerRepository.getById(Id);

    // Assert that the retrieved customer is not null
    assertNotNull(customer);
  }

  @Test
  @Order(3)
  public void testUpdateCustomer() {
    // Define test data
    Long Id = 1L;
    String Name = "John Doe Updated";
    String Street = "123 Main St";
    String HouseNumber = "456";
    String ZipCode = "12345";
    String Place = "City";
    String EmailAddress = "John@Doe.com";
    String PhoneNumber = "123-456-7890";

    // Create a Customer object with the test data
    Customer customer = Customer.builder().name(Name).street(Street).houseNumber(HouseNumber)
        .zipCode(ZipCode).place(Place).emailAddress(EmailAddress).phoneNumber(PhoneNumber).build();

    // Save the customer to the database
    Customer updatedCustomer = customerRepository.save(customer);

    // Assert that the retrieved customer is not null
    assertNotNull(updatedCustomer);

    // Assert that the retrieved customer's name matches the expected name
    assertEquals(Name, updatedCustomer.getName());
  }

  @Test
  @Order(4)
  public void testDeleteCustomer() {
    // Define test data
    Long Id = 1L;

    // Assert that the retrieved customer id is not null
    assertNotNull(Id);

    // Delete the customer from the database
    customerRepository.deleteById(Id);
  }

}
