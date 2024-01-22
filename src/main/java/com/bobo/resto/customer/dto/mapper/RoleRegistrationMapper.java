package com.bobo.resto.customer.dto.mapper;

import com.bobo.resto.customer.dto.RoleRegistrationDTO;
import com.bobo.resto.customer.entity.Role;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoleRegistrationMapper implements Function<RoleRegistrationDTO, Role> {
    @Override
    public Role apply(RoleRegistrationDTO roleRegistrationDTO) {
        return new Role(
                roleRegistrationDTO.name()
        );
    }
}
