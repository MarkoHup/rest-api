package com.carlease.restapi.controllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.carlease.restapi.controller.CustomerController;
import com.carlease.restapi.dto.CustomerDTO;
import com.carlease.restapi.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(CustomerController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerControllerTest {

  CustomerDTO customer;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CustomerService customerService;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setup() {

    customer = CustomerDTO.builder()
        .id(1L)
        .Name("John Doe")
        .Street("de Streek")
        .HouseNumber("19a")
        .Place("Peize")
        .ZipCode("12345")
        .EmailAddress("john@gmail.com")
        .PhoneNumber("123444")
        .build();

  }

  //Post Controller
  @Test
  @Order(1)
  public void saveCustomerTest() throws Exception {
    // precondition
    given(customerService.createCustomer(any(CustomerDTO.class))).willReturn(customer);

    // action
    ResultActions response = mockMvc.perform(post("/api/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(customer)));

    // verify
    response.andDo(print()).
        andExpect(status().isCreated())
        .andExpect(jsonPath("$.Name",
            is(customer.getName())))
        .andExpect(jsonPath("$.EmailAddress",
            is(customer.getEmailAddress())));
  }

  //Get Controller
  @Test
  @Order(2)
  public void getCustomerTest() throws Exception {
    // precondition
    List<CustomerDTO> customersList = new ArrayList<>();
    customersList.add(customer);
    customersList.add(CustomerDTO.builder().id(2L).Name("Sam").Place("Peize").Street("de streek")
        .HouseNumber("19").EmailAddress("sam@gmail.com").build());
    given(customerService.getAllCustomers()).willReturn(customersList);

    // action
    ResultActions response = mockMvc.perform(get("/api/customers"));

    // verify the output
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.size()",
            is(customersList.size())));

  }

  //get by Id controller
  @Test
  @Order(3)
  public void getByIdCustomerTest() throws Exception {
    // precondition
    given(customerService.getCustomerById(customer.getId())).willReturn(customer);

    // action
    ResultActions response = mockMvc.perform(get("/api/customers/{id}", customer.getId()));

    // verify
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.firstName", is(customer.getName())))
        .andExpect(jsonPath("$.email", is(customer.getEmailAddress())));

  }


  //Update customer
  @Test
  @Order(4)
  public void updateCustomerTest() throws Exception {
    // precondition
    given(customerService.getCustomerById(customer.getId())).willReturn(customer);
    customer.setName("Max");
    customer.setEmailAddress("max@gmail.com");
    given(customerService.updateCustomer(customer.getId(), customer)).willReturn(customer);

    // action
    ResultActions response = mockMvc.perform(put("/api/customers/{id}", customer.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(customer)));

    // verify
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.firstName", is(customer.getName())))
        .andExpect(jsonPath("$.email", is(customer.getEmailAddress())));
  }


  // delete customer
  @Test
  public void deleteCustomerTest() throws Exception {
    // precondition
    willDoNothing().given(customerService).deleteCustomer(customer.getId());

    // action
    ResultActions response = mockMvc.perform(delete("/api/customers/{id}", customer.getId()));

    // then - verify the output
    response.andExpect(status().isOk())
        .andDo(print());
  }
}
