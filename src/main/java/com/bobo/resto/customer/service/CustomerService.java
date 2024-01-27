package com.bobo.resto.customer.service;

import com.bobo.resto.customer.dto.CustomerDTO;
import com.bobo.resto.customer.dto.CustomerRegistrationDTO;
import com.bobo.resto.email.dto.EmailDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<String> registerCustomer(CustomerRegistrationDTO customerRegistrationDTO);
    ResponseEntity<String> deleteCustomer(Long customerId);
    ResponseEntity<String> updateCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getUsers(int pageNumber, int pageSize);
}