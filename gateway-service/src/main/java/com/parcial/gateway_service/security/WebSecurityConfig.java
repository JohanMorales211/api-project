package com.parcial.gateway_service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges
                .pathMatchers("/api/auth/**").permitAll() // Authentication routes
                .pathMatchers("/api/client/**").hasAnyRole(ADMIN, USER) // User related routes
                .pathMatchers("/api/hostings/**").hasRole(ADMIN) // Hosting management routes
                .pathMatchers("/api/reservation/**").hasAnyRole(ADMIN, USER) // Reservation management routes
                .pathMatchers("/api/flights/**").hasRole(ADMIN) // Flight management routes
                .pathMatchers("/api/destiny/**").hasRole(ADMIN) // Destiny management routes
                .pathMatchers("/api/origin/**").hasRole(ADMIN) // Origin management routes
                .anyExchange().authenticated() // Any other request must be authenticated
        );

        http.oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
        );

        http.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }
}

