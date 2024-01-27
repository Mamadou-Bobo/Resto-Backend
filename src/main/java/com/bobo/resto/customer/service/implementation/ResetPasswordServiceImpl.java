package com.bobo.resto.customer.service.implementation;

import com.bobo.resto.customer.entity.Customer;
import com.bobo.resto.customer.entity.ResetPassword;
import com.bobo.resto.customer.repository.CustomerRepository;
import com.bobo.resto.customer.repository.ResetPasswordRepository;
import com.bobo.resto.customer.service.ResetPasswordService;
import com.bobo.resto.email.dto.EmailDTO;
import com.bobo.resto.email.service.EmailService;
import com.bobo.resto.shared.exception.ResourceNotFoundException;
import com.bobo.resto.shared.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.bobo.resto.shared.util.Constant.*;

@Service
@RequiredArgsConstructor
@Slf4j
class ResetPasswordServiceImpl implements ResetPasswordService {

    private final ResetPasswordRepository resetPasswordRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    @Override
    public void saveResetPassword(String email, String code) {
        log.info("Saving reset password record");

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email address not found"));

        ResetPassword resetPassword = new ResetPassword(
                Utils.generateToken(),
                customer,
                Utils.generateTokenExpiryDate(RESET_PASSWORD_CODE_VALIDITY),
                new Date(),
                code,
                true
        );

        resetPasswordRepository.save(resetPassword);
    }

    @Override
    public ResponseEntity<String> sendResetPasswordCode(EmailDTO emailDTO) {
        if(customerRepository.findByEmail(emailDTO.recipient()).isEmpty()) {
            return new ResponseEntity<>("Email address not found", HttpStatus.NOT_FOUND);
        }

        Customer customer = customerRepository.findByEmail(emailDTO.recipient()).get();

        String code = Utils.generateCode(RESET_PASSWORD_CODE_LENGTH);

        emailDTO = new EmailDTO(
                customer.getEmail(),
                "Reset Password Code",
                RESET_PASSWORD_TEMPLATE_NAME,
                customer.getFirstName(),
                code,
                RESET_PASSWORD_CODE_VALIDITY
        );

        var response = emailService.sendSimpleEmail(emailDTO);

        saveResetPassword(emailDTO.recipient(), code);

        return response;
    }
}