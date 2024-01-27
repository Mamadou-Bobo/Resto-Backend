package com.bobo.resto.customer.repository;

import com.bobo.resto.customer.dto.CustomerDTO;
import com.bobo.resto.customer.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);

    @Query(value = "select * from customer where id != value", nativeQuery = true)
    List<CustomerDTO> getUsers(String value, Pageable pageable);
}
