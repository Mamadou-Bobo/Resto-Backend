package com.bobo.resto.customer.dto.mapper;

import com.bobo.resto.customer.dto.CustomerDTO;
import com.bobo.resto.customer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDTOMapper implements Function<Customer,CustomerDTO> {

    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getCreatedAt(),
                customer.getLastModified(),
                customer.getRoles()
        );
    }
}
