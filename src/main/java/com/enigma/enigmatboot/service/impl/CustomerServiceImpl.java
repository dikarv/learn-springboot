package com.enigma.enigmatboot.service.impl;

import com.enigma.enigmatboot.constant.ResponseMessage;
import com.enigma.enigmatboot.dto.CustomerSearchDTO;
import com.enigma.enigmatboot.entity.Customer;
import com.enigma.enigmatboot.exception.DataNotFoundException;
import com.enigma.enigmatboot.repository.CustomerRepository;
import com.enigma.enigmatboot.repository.ProductRepository;
import com.enigma.enigmatboot.service.CustomerService;
import com.enigma.enigmatboot.service.ProductService;
import com.enigma.enigmatboot.spesification.CustomerSpesification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Override
    public Customer saveCustomer(Customer customer) {
            return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String id) {
        validatePresent(id);
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO) {
        Specification<Customer> customerSpecification = CustomerSpesification.getSpesification(customerSearchDTO);
        return customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    public List<Customer> getCustomerByName(String firstName, String lastName) {
        return customerRepository.findCustomerByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }

    @Override
    public List<Customer> getActiveCustomer() {
        return customerRepository.findActiveCustomer();
    }

    @Override
    public List<Customer> getNonActiveCustomer(String firstName, String lastName) {
        return customerRepository.findNonActiveCustomer(firstName, lastName);
    }

    @Override
    public void updateCustomerStatus(String id) {
        customerRepository.updateCustomerStatus(id);
    }


    private void validatePresent(String id) {
        if (!customerRepository.findById(id).isPresent()){
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "customer", id);
            throw new DataNotFoundException(message);
        }
    }

    public Boolean userNameExist(String username){
        return customerRepository.existsByUserName(username);
    }


}
