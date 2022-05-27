package com.enigma.enigmatboot.controller;

import com.enigma.enigmatboot.constant.ApiUrlConstant;
import com.enigma.enigmatboot.constant.ResponseMessage;
import com.enigma.enigmatboot.dto.CustomerSearchDTO;
import com.enigma.enigmatboot.entity.Customer;
import com.enigma.enigmatboot.service.CustomerService;
import com.enigma.enigmatboot.utils.PageResponseWrapper;
import com.enigma.enigmatboot.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {


    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Response<Customer>> createCustomer(@RequestBody Customer customer){
        Response<Customer> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "customer");
        response.setMessage(message);
        response.setData(customerService.saveCustomer(customer));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{customerId}")
    // customer/{id}
    public Customer getCustomerById(@PathVariable String customerId){
        return customerService.getCustomerById(customerId);
    }

//    @GetMapping("/customers")
//    public List<Customer> getAllCustomer(){
//        return customerService.getAllCustomer();
//    }

//    @GetMapping("/customers")
//    public List<Customer> getAllCustomer(@RequestParam(name = "firstName", defaultValue = "")String firstName,
//                                         @RequestParam(name = "lastName", defaultValue = "") String lastName){
//        return customerService.getCustomerByName(firstName, lastName);
//    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestParam String id){
        customerService.deleteCustomer(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PageResponseWrapper<Customer> searchCustomerPerPage(@RequestParam(name = "firstName", required = false) String firstName,
                                                                   @RequestParam(name = "lastName", required = false) String lastName,
                                                                   @RequestParam(name = "dateOfBirth", required = false) String dateOfBirth,
                                                                   @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                   @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                                                   @RequestParam(name = "sortBy", defaultValue = "firstName") String sortBy,
                                                                   @RequestParam(name = "direction", defaultValue = "ASC") String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO(firstName, lastName, Date.valueOf(dateOfBirth));
        Page<Customer> customerPage = customerService.getCustomerPerPage(pageable, customerSearchDTO);
        return new PageResponseWrapper<Customer>(customerPage);
    }

    @GetMapping("/active")
    public List<Customer> getActiveCustomer(){
        return customerService.getActiveCustomer();
    }

    @GetMapping("/not-active")
    public List<Customer> getNotActiveCustomer(@RequestParam(name = "firstName") String firstName,
                                               @RequestParam(name = "lastName") String lastName){
        return customerService.getNonActiveCustomer(firstName, lastName);
    }

    @PutMapping("/active")
    public void changeCustomerStatusActive(@RequestParam(name = "id") String id){
        customerService.updateCustomerStatus(id);
    }

}
