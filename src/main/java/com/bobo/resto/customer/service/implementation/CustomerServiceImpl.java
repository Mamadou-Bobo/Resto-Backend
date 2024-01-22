package com.bobo.resto.customer.service.implementation;

import com.bobo.resto.customer.dto.CustomerDTO;
import com.bobo.resto.customer.dto.CustomerRegistrationDTO;
import com.bobo.resto.customer.dto.mapper.CustomerDTOMapper;
import com.bobo.resto.customer.dto.mapper.CustomerRegistrationDTOMapper;
import com.bobo.resto.customer.entity.Customer;
import com.bobo.resto.customer.repository.CustomerRepository;
import com.bobo.resto.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerRegistrationDTOMapper customerRegistrationDTOMapper;
    private final CustomerDTOMapper customerDTOMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> registerCustomer(CustomerRegistrationDTO customerRegistrationDTO) {
        log.info("Adding new user");
        Customer customer = customerRegistrationDTOMapper
                .apply(customerRegistrationDTO);
        customer.setPassword(
                passwordEncoder
                .encode(customerRegistrationDTO.password()));
        customerRepository.save(customer);
        return ResponseEntity.ok("User registered successfully");
    }

    @Override
    public ResponseEntity<String> deleteCustomer(Long customerId) {
        log.info("Deleting user");

        if(customerRepository.findById(customerId).isPresent()) {
            Customer user = customerRepository.findById(customerId).get();
            customerRepository.delete(user);
            return ResponseEntity.ok("User account deleted successfully");
        }

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> updateCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public List<CustomerDTO> getUsers(int pageNumber, int pageSize) {
        log.info("Fetching users");

        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        return customerRepository
                .findAll(pageable)
                .stream()
                .map(customerDTOMapper)
                .collect(Collectors.toList());
    }
}
