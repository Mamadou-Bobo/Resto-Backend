package com.bobo.resto.email.service;

import com.bobo.resto.email.dto.EmailDTO;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static com.bobo.resto.shared.util.Constant.RESET_PASSWORD_TEMPLATE_NAME;

@Service
@Slf4j
@RequiredArgsConstructor
class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    @Override
    public ResponseEntity<String> sendSimpleEmail(EmailDTO emailDTO) {
        log.info("Sending email to {}...", emailDTO.recipient());

        if(emailDTO.recipient().isEmpty()) {
            return new ResponseEntity<>(
                    "Please provide the recipient email address",
                    HttpStatus.BAD_REQUEST
            );
        }

        if(emailDTO.firstName().isEmpty()) {
            return new ResponseEntity<>(
                    "Please provide the recipient first name",
                    HttpStatus.BAD_REQUEST
            );
        }

        if(emailDTO.code().isEmpty()) {
            return new ResponseEntity<>(
                    "Code cannot be null",
                    HttpStatus.BAD_REQUEST
            );
        }

        try{
            Context context = new Context();

            context.setVariable("firstName", emailDTO.firstName());
            context.setVariable("code", emailDTO.code());
            context.setVariable("codeValidity", emailDTO.codeValidity());

            String process = templateEngine.process(RESET_PASSWORD_TEMPLATE_NAME, context);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setSubject(emailDTO.subject());
            helper.setText(process, true);
            helper.setTo(emailDTO.recipient());

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
        return ResponseEntity.ok("Reset code successfully sent. Please verify your mail");
    }
}