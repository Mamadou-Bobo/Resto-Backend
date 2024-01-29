package com.bobo.resto.customer.dto;

public record ResetPasswordRequestDTO(
        String code,
        String username,
        String password
) {
}
