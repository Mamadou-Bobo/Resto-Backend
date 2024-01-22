package com.bobo.resto.authentication.service;

import com.bobo.resto.customer.entity.Customer;
import com.bobo.resto.customer.repository.CustomerRepository;
import com.bobo.resto.shared.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Mamadou Bobo on 22/01/2023
 * @project CsvMapping
 */

@Service(value = "detailService")
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("Username " + username + " not found in the database"));

        log.info("User found in the database: {}", username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        customer.getRoles().forEach(role -> authorities
                .add(new SimpleGrantedAuthority(role.getName()))
        );

        return new org.springframework.security.core.userdetails.User(
                customer.getUsername(),
                customer.getPassword(), authorities);
    }
}
