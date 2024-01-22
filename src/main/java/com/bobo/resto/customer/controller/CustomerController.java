package com.bobo.resto.customer.controller;

import com.bobo.resto.customer.dto.CustomerDTO;
import com.bobo.resto.customer.dto.CustomerRegistrationDTO;
import com.bobo.resto.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bobo.resto.shared.utils.Constant.BASE_API;

@RestController
@RequestMapping(BASE_API + "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody
                                                   CustomerRegistrationDTO
                                                           customerRegistrationDTO) {
        return customerService.registerCustomer(customerRegistrationDTO);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    @PreAuthorize("hasAuthority('ROLE_SYS_ADMIN')")
    public List<CustomerDTO> fetchUsers(@PathVariable int pageNumber,
                                                      @PathVariable int pageSize) {
        return customerService.getUsers(pageNumber,pageSize);
    }
}
