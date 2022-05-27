package com.enigma.enigmatboot.security.services;

import com.enigma.enigmatboot.entity.Customer;
import com.enigma.enigmatboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUserName(username)
        .orElseThrow(() -> new  UsernameNotFoundException("User not found with username "+ username));
        return CustomerDetailsImpl.build(customer);
    }
}
