package com.bobo.resto.authentication.service;

import com.bobo.resto.authentication.dto.JwtResponseDto;
import com.bobo.resto.authentication.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.bobo.resto.shared.util.Constant.ACCESS_TOKEN_DURATION;
import static com.bobo.resto.shared.util.Constant.REFRESH_TOKEN_DURATION;

/**
 * @author Mamadou Bobo on 21/01/2023
 * @project CsvMapping
 */

@Component
@Slf4j
public class JwtService {

    private static final String SECRET = "327235753778214125442A472D4B6150645367566B59703373367639792F423F";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public JwtResponseDto generateToken(String username, Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims,username,authentication);
    }

    private JwtResponseDto createToken(Map<String, Object> claims, String username, Authentication authentication) {
        String access_token = JwtTokenUtil.generateToken(claims,username,authentication,getSignKey(),ACCESS_TOKEN_DURATION);

        String refresh_token = JwtTokenUtil.generateToken(claims,username,authentication,getSignKey(),REFRESH_TOKEN_DURATION);

        return new JwtResponseDto(access_token,refresh_token);
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}