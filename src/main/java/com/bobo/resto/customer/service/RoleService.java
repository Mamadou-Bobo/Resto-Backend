package com.bobo.resto.customer.service;

import com.bobo.resto.customer.dto.RoleRegistrationDTO;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<String> addRole(RoleRegistrationDTO roleRegistrationDTO);
}
