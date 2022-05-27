package com.enigma.enigmatboot.controller;

import com.enigma.enigmatboot.entity.Customer;
import com.enigma.enigmatboot.entity.ERole;
import com.enigma.enigmatboot.entity.Role;
import com.enigma.enigmatboot.repository.RoleRepository;
import com.enigma.enigmatboot.security.jwt.JwtUtils;
import com.enigma.enigmatboot.security.payload.request.LoginRequest;
import com.enigma.enigmatboot.security.payload.request.SignupRequest;
import com.enigma.enigmatboot.security.payload.response.JwtResponse;
import com.enigma.enigmatboot.security.payload.response.MessageResponse;
import com.enigma.enigmatboot.security.services.CustomerDetailsImpl;
import com.enigma.enigmatboot.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    RoleRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
//    @PreAuthorize("hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getUserPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
        List<String> roles = customerDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, customerDetails.getId(),
                customerDetails.getFirstName(), customerDetails.getLastName(),customerDetails.getDateOfBirth(),
                customerDetails.getUserName(), customerDetails.getStatus(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        if(customerService.userNameExist(signupRequest.getUserName())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));
        }

        Customer customer = new Customer(signupRequest.getFirstName(),
                                        signupRequest.getLastName(),
                                        signupRequest.getDateOfBirth(),
                                        signupRequest.getUserName(),
                                        signupRequest.getStatus(),
                passwordEncoder.encode(signupRequest.getPassword())
                );
        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null){
            Role userRole = repository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminRole = repository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = repository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() ->  new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }
        customer.setRoles(roles);
        customerService.saveCustomer(customer);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



}
