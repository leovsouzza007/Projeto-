package com.sistema.loja.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // ✅ Swagger / OpenAPI - liberados sem autenticação
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/api-docs",
                    "/api-docs/**",
                    "/v3/api-docs",
                    "/v3/api-docs/**"
                ).permitAll()

                // ✅ Todos os endpoints da API - liberados por enquanto
                // Quando implementar JWT, trocar por .authenticated()
                .requestMatchers("/api/**").permitAll()

                // ✅ Frontend estático
                .requestMatchers(
                    "/",
                    "/index.html",
                    "/static/**",
                    "/produtos"
                ).permitAll()

                // 🔒 Qualquer outra rota requer autenticação
                .anyRequest().authenticated()
            );

        return http.build();
    }
}