
package com.example.fg_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Jwtauthenticator jwtAuthenticator;

    public SecurityConfig(Jwtauthenticator jwtAuthenticator) {
        this.jwtAuthenticator = jwtAuthenticator;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            // Disable CSRF for our stateless API
            .csrf(csrf -> csrf.disable())

            // No HTTP sessions; JWT is used for authentication
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Define endpoint access
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/users/register",
                    "/api/users/login"
                ).permitAll()

                .anyRequest().authenticated()
            )

            // Register our JWT filter
            .addFilterBefore(
                jwtAuthenticator,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
