package com.bobo.resto.authentication.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Mamadou Bobo on 22/01/2023
 * @project CsvMapping
 */
public class JwtTokenUtil {

    public static String generateToken(Map<String, Object> claims, String username, Authentication authentication, Key signKey, int tokenDuration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenDuration))
                .claim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .signWith(signKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
