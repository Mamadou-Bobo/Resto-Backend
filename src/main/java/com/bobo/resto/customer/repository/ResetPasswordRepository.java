package com.bobo.resto.customer.repository;

import com.bobo.resto.customer.entity.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPassword,Long> {
    Optional<ResetPassword> findByCode(String code);
}
