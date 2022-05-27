package com.enigma.enigmatboot.service.impl;

import com.enigma.enigmatboot.entity.Customer;
import com.enigma.enigmatboot.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Autowired
    MockMvc mockMvc;

    private Customer customer;
    private Customer output;

    @BeforeEach
    void setup(){
        customer = new Customer("c01", "xyz","xyz",new Date(), "xyasdajskdha01", "xyz" , 1);
        output = new Customer();
        output.setId("c01");
        output.setFirstName(customer.getFirstName());
        output.setLastName(customer.getLastName());
        output.setPassword(customer.getPassword());
        output.setStatus(customer.getStatus());
        output.setUserName(customer.getUserName());
        output.setDateOfBirth(customer.getDateOfBirth());

        when(customerRepository.save(any())).thenReturn(output);
    }

    @Test
    void saveCustomer() {
    }

    @Test
    void getCustomerById() {
        customerRepository.save(customer);
        given(customerRepository.findById("c01")).willReturn(Optional.ofNullable(output));
        Customer returned = customerService.getCustomerById("c01");
        verify(customerRepository).findById("c01");
        assertNotNull(returned);
    }

    @Test
    void getAllCustomer() {
        customerRepository.save(customer);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerService.getAllCustomer()).thenReturn(customers);
        assertEquals(1, customerService.getAllCustomer().size());
    }

    @Test
    void deleteCustomer() {
        customerRepository.save(customer);
        customerService.deleteCustomer("C01");
        assertEquals(0, customerRepository.findAll().size());
    }

    @Test
    void getCustomerPerPage() {
    }

    @Test
    void getCustomerByName() {
    }
}
