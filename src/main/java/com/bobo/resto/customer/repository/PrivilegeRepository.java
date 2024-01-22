package com.bobo.resto.customer.repository;

import com.bobo.resto.customer.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Optional<Privilege> findByName(String name);
}
