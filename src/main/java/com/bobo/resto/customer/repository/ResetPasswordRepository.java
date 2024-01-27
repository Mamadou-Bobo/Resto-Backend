package com.bobo.resto.customer.repository;

import com.bobo.resto.customer.entity.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPassword,Long> {
}
