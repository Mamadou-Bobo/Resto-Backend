package com.bobo.resto.customer.service;

import com.bobo.resto.customer.dto.PrivilegeRegistrationDTO;
import org.springframework.http.ResponseEntity;

public interface PrivilegeService {
    ResponseEntity<String> addPrivilege(PrivilegeRegistrationDTO privilegeRegistrationDTO);
}
