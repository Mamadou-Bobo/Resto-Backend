package com.bobo.resto.shared.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if(authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        User customer = (User) authentication.getPrincipal();

        return Optional.ofNullable(customer.getUsername());
    }
}