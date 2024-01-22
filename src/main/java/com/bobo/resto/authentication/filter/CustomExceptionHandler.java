package com.bobo.resto.authentication.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Mamadou Bobo on 28/08/2023
 * @project CsvMapping
 */

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {

        ProblemDetail errorDetail = null;

        if(exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(
                            HttpStatusCode.valueOf(401),
                            exception.getMessage()
                    );

            errorDetail.setProperty("reason", "Username or Password incorrect");
        }

        if(exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(
                            HttpStatusCode.valueOf(403),
                            exception.getMessage()
                    );

            errorDetail.setProperty("reason", "Access denied: Not authorized");
        }

        if(exception instanceof SignatureException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(
                            HttpStatusCode.valueOf(403),
                            exception.getMessage()
                    );

            errorDetail.setProperty("reason", "Access denied: JWT signature is not valid");
        }

        if(exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(
                            HttpStatusCode.valueOf(403),
                            exception.getMessage()
                    );

            errorDetail.setProperty("reason", "Access denied: JWT token already expired");
        }

        return errorDetail;
    }

}
