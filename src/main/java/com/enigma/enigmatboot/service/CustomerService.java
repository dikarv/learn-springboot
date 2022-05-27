package com.enigma.enigmatboot.service;

import com.enigma.enigmatboot.dto.CustomerSearchDTO;
import com.enigma.enigmatboot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);
    public Customer getCustomerById(String id);
    public List<Customer> getAllCustomer();
    public void deleteCustomer(String id);
    public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);
    public List<Customer> getCustomerByName(String firstName, String lastName);
    public List<Customer> getActiveCustomer();
    public List<Customer> getNonActiveCustomer(String firstName, String lastName);
    public void updateCustomerStatus(String id);

}
