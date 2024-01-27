package com.bobo.resto.email.dto;

public record EmailDTO(
        String recipient,
        String subject,
        String emailTemplate,
        String firstName,
        String code,
        int codeValidity
) {
}