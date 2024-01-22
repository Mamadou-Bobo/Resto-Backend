package com.bobo.resto.customer.dto;

import com.bobo.resto.customer.entity.Role;

import java.util.Collection;

public record CustomerRegistrationDTO(
        String firstName,
        String lastName,
        String username,
        String password,
        String email,
        Collection<Role> roles
) {
}
