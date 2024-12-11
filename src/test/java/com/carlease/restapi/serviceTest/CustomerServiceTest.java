package com.carlease.restapi.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.carlease.restapi.dto.CustomerDTO;
import com.carlease.restapi.model.Customer;
import com.carlease.restapi.repository.CustomerRepository;
import com.carlease.restapi.service.CustomerService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerService customerService;

  private CustomerDTO customerDTO;

  private Customer customer;

  @BeforeEach
  public void setup() {

    customerDTO = CustomerDTO.builder()
        .id(1L)
        .Name("John")
        .Street("123 Main St")
        .HouseNumber("456")
        .ZipCode("12345")
        .Place("City")
        .PhoneNumber("123-456-7890")
        .EmailAddress("john@gmail.com")
        .build();

    customer = Customer.builder()
        .id(1L)
        .name("John")
        .street("123 Main St")
        .houseNumber("456")
        .zipCode("12345")
        .place("City")
        .phoneNumber("123-456-7890")
        .emailAddress("john@gmail.com")
        .build();
  }


  @Test
  @Order(1)
  public void saveCustomerTest() {
    // precondition
    given(customerRepository.save(customer)).willReturn(customer);

    //action
    CustomerDTO savedCustomer = customerService.createCustomer(customerDTO);

    // verify the output
    System.out.println(savedCustomer);
    assertThat(savedCustomer).isNotNull();
  }

  @Test
  @Order(2)
  public void getCustomerById() {
    // precondition
    given(customerRepository.findById(1L)).willReturn(Optional.ofNullable(customer));

    // action
    CustomerDTO existingCustomer = customerService.getCustomerById(customerDTO.getId());

    // verify
    System.out.println(existingCustomer);
    assertThat(existingCustomer).isNotNull();

  }


  @Test
  @Order(3)
  public void getAllCustomer() {
    Customer customer1 = Customer.builder()
        .id(2L)
        .name("Sam")
        .emailAddress("sam@gmail.com")
        .build();

    // precondition
    given(customerRepository.findAll()).willReturn(List.of(customer, customer1));

    // action
    List<CustomerDTO> customerList = customerService.getAllCustomers();

    // verify
    System.out.println(customerList);
    assertThat(customerList).isNotNull();
    assertThat(customerList.size()).isGreaterThan(1);
  }

  @Test
  @Order(4)
  public void updateCustomer() {

    // precondition
    given(customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));
    customerDTO.setEmailAddress("max@gmail.com");
    customerDTO.setName("Max");
    given(customerRepository.save(customer)).willReturn(customer);

    // action
    CustomerDTO updatedCustomer = customerService.updateCustomer(customerDTO.getId(), customerDTO);

    // verify
    System.out.println(updatedCustomer);
    assertThat(updatedCustomer.getEmailAddress()).isEqualTo("max@gmail.com");
    assertThat(updatedCustomer.getName()).isEqualTo("Max");
  }

}
