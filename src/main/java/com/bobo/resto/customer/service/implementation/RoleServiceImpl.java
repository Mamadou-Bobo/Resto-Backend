package com.bobo.resto.customer.service.implementation;

import com.bobo.resto.customer.dto.RoleRegistrationDTO;
import com.bobo.resto.customer.dto.mapper.RoleRegistrationMapper;
import com.bobo.resto.customer.entity.Role;
import com.bobo.resto.customer.repository.RoleRepository;
import com.bobo.resto.customer.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class RoleServiceImpl implements RoleService {

    private final RoleRegistrationMapper roleRegistrationMapper;
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<String> addRole(RoleRegistrationDTO roleRegistrationDTO) {
        if(roleRepository.findByName(roleRegistrationDTO.name()).isEmpty()) {
            Role role = roleRegistrationMapper.apply(roleRegistrationDTO);
            roleRepository.save(role);
            return ResponseEntity.ok("Role saved successfully");
        }

        return new ResponseEntity<>(
                "Role " + roleRegistrationDTO.name() + " already exists",
                HttpStatus.CONFLICT
        );
    }
}
