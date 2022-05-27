package com.enigma.enigmatboot.controller;

import com.enigma.enigmatboot.entity.Customer;
import com.enigma.enigmatboot.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    CustomerController customerController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private Customer customer;

    @BeforeEach
    void setup(){
        customer = new Customer();
        customer.setId("011");
        customer.setUserName("asd");
        customer.setPassword("asd");
        customer.setFirstName("asd");
        customer.setLastName("asd");
        customer.setDateOfBirth(new Date());
        customer.setStatus(1);
    }

    public static String asJsonString(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void createCustomer() throws Exception {
        given(customerService.saveCustomer(any(Customer.class))).willAnswer((invocation) -> invocation.getArgument(0));
        mockMvc.perform(put("/customers")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(customer)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.userName", Matchers.is(customer.getUserName())));
    }

    @Test
    void getCustomerById() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void searchCustomerPerPage() {
    }
}
