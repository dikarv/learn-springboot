package com.enigma.enigmatboot.repository;

import com.enigma.enigmatboot.entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EntityManager entityManager;

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

    @Test
    void findCustomerByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase() {
    }

    @Test
    void findAll() {
    }

    @Test
    void shouldSave_Customer(){
        Customer input = customerRepository.save(customer);
        assertNotNull(entityManager.find(Customer.class, input.getId()));
    }

    @AfterEach
    void deleteAll(){
        customerRepository.deleteAll();
    }
}
