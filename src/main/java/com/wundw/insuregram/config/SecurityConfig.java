package com.wundw.insuregram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users", "/api/users/**").permitAll()
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable()) // Optional: Disable CSRF for simplicity
            .build();
    }
}

