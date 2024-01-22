package com.bobo.resto.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mamadou Bobo on 22/01/2023
 * @project CsvMapping
 */

@Data
@AllArgsConstructor
public class JwtResponseDto {
    private String access_token;
    private String refresh_token;
}
