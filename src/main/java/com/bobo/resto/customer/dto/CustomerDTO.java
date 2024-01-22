package com.bobo.resto.customer.dto;

import com.bobo.resto.customer.entity.Role;

import java.time.LocalDateTime;
import java.util.Collection;

public record CustomerDTO(
        String firstName,
        String lastName,
        String username,
        String email,
        LocalDateTime createdAt,
        LocalDateTime lastModified,
        Collection<Role> roles
) {
}
