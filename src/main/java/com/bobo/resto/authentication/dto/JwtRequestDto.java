package com.bobo.resto.authentication.dto;

import lombok.Data;

/**
 * @author Mamadou Bobo on 21/01/2023
 * @project CsvMapping
 */

@Data
public class JwtRequestDto {
    private String username;
    private String password;
}
