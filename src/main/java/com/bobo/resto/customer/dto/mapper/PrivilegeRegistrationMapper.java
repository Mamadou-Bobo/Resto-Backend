package com.bobo.resto.customer.dto.mapper;

import com.bobo.resto.customer.dto.PrivilegeRegistrationDTO;
import com.bobo.resto.customer.entity.Privilege;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PrivilegeRegistrationMapper implements Function<PrivilegeRegistrationDTO, Privilege> {
    @Override
    public Privilege apply(PrivilegeRegistrationDTO privilegeRegistrationDTO) {
        return new Privilege(
                privilegeRegistrationDTO.name()
        );
    }
}
