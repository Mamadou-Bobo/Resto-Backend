package com.bobo.resto.customer.service;

import com.bobo.resto.customer.dto.ResetPasswordRequestDTO;
import com.bobo.resto.email.dto.EmailDTO;
import org.springframework.http.ResponseEntity;

public interface ResetPasswordService {
    void saveResetPassword(String email, String code);
    ResponseEntity<String> sendResetPasswordCode(EmailDTO emailDTO);
    ResponseEntity<String> resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO);
}