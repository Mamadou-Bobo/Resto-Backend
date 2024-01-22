package com.bobo.resto.customer.dto.mapper;

import com.bobo.resto.customer.dto.CustomerRegistrationDTO;
import com.bobo.resto.customer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerRegistrationDTOMapper implements Function<CustomerRegistrationDTO, Customer> {

    @Override
    public Customer apply(CustomerRegistrationDTO customerRegistrationDTO) {
        return new Customer(
                customerRegistrationDTO.firstName(),
                customerRegistrationDTO.lastName(),
                customerRegistrationDTO.username(),
                customerRegistrationDTO.password(),
                customerRegistrationDTO.email(),
                customerRegistrationDTO.roles()
        );
    }
}
