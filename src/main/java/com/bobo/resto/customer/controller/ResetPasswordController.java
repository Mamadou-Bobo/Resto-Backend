package com.bobo.resto.customer.controller;

import com.bobo.resto.customer.dto.ResetPasswordRequestDTO;
import com.bobo.resto.customer.service.ResetPasswordService;
import com.bobo.resto.email.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bobo.resto.shared.util.Constant.BASE_API;

@RestController
@RequestMapping(BASE_API + "/password")
@RequiredArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    @PostMapping("/verify-account")
    public ResponseEntity<String> sendResetPasswordCode(@RequestBody EmailDTO emailDTO) {
        return resetPasswordService.sendResetPasswordCode(emailDTO);
    }

    @PostMapping("reset-password")
    public  ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDTO
                                                             resetPasswordRequestDTO) {
        return resetPasswordService.resetPassword(resetPasswordRequestDTO);
    }
}