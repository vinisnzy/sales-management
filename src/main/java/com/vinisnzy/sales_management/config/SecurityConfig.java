package com.vinisnzy.sales_management.config;

import com.vinisnzy.sales_management.filter.SecurityFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.Instant;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String adminRole = "ADMIN";
        String sellerRole = "SELLER";
        String clientsEndpoint = "/clients/**";
        String productsEndpoint = "/products/**";
        String salesEndpoint = "/sales/**";

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",
                                "/h2-console/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.DELETE, clientsEndpoint).hasRole(adminRole)
                        .requestMatchers(HttpMethod.DELETE, productsEndpoint).hasRole(adminRole)
                        .requestMatchers(HttpMethod.POST, productsEndpoint).hasRole(adminRole)
                        .requestMatchers(HttpMethod.PUT, productsEndpoint).hasRole(adminRole)
                        .requestMatchers(HttpMethod.GET, productsEndpoint).hasAnyRole(adminRole, sellerRole)
                        .requestMatchers(salesEndpoint).hasAnyRole(adminRole, sellerRole)
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint()))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            String body = "{"
                    + "\"timestamp\": \"" + Instant.now() + "\","
                    + "\"status\": " + HttpServletResponse.SC_UNAUTHORIZED + ","
                    + "\"error\": \"Unauthorized\","
                    + "\"message\": \"" + authException.getMessage() + "\","
                    + "\"path\": \"" + request.getRequestURI() + "\""
                    + "}";
            response.getWriter().write(body);
        };
    }
}
