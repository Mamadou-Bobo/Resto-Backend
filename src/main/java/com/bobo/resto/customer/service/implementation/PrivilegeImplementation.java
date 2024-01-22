package com.bobo.resto.customer.service.implementation;

import com.bobo.resto.customer.dto.PrivilegeRegistrationDTO;
import com.bobo.resto.customer.dto.mapper.PrivilegeRegistrationMapper;
import com.bobo.resto.customer.entity.Privilege;
import com.bobo.resto.customer.repository.PrivilegeRepository;
import com.bobo.resto.customer.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrivilegeImplementation implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeRegistrationMapper privilegeRegistrationMapper;

    @Override
    public ResponseEntity<String> addPrivilege(PrivilegeRegistrationDTO privilegeRegistrationDTO) {
        if(privilegeRepository.findByName(privilegeRegistrationDTO.name()).isEmpty()) {
            Privilege privilege = privilegeRegistrationMapper.apply(privilegeRegistrationDTO);
            privilegeRepository.save(privilege);
            return ResponseEntity.ok("Privilege saved successfully");
        }
        return new ResponseEntity<>(
                "Privilege already exists",
                HttpStatus.CONFLICT
        );
    }
}
