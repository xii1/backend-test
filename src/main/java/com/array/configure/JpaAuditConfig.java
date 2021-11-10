package com.array.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

/**
 * @author XIII
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof AnonymousAuthenticationToken) {
                return Optional.of("system");
            } else {
                return Optional.of(auth.getName());
            }
        };
    }
}
