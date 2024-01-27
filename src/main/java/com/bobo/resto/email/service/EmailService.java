package com.bobo.resto.email.service;

import com.bobo.resto.email.dto.EmailDTO;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    ResponseEntity<String> sendSimpleEmail(EmailDTO emailDTO);
}