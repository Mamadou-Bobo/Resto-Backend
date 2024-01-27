package com.bobo.resto.authentication.controller;

import com.bobo.resto.authentication.dto.JwtRequestDto;
import com.bobo.resto.authentication.dto.JwtResponseDto;
import com.bobo.resto.authentication.service.JwtService;
import com.bobo.resto.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bobo.resto.shared.util.Constant.BASE_API;

/**
 * @author Mamadou Bobo on 21/01/2023
 * @project CsvMapping
 */

@RestController
@RequestMapping(BASE_API + "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public JwtResponseDto authenticate(@RequestBody JwtRequestDto jwtRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                                            new UsernamePasswordAuthenticationToken(
                                                jwtRequestDto.getUsername(),
                                                jwtRequestDto.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(jwtRequestDto.getUsername(),authentication);
        }

        throw new ResourceNotFoundException("Invalid user request");
    }

}
