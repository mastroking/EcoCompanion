package com.example.EcoCompanion.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Whitelist per Swagger
    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    /**
     * Configurazione Security per ambiente di sviluppo (dev)
     * Swagger accessibile senza login, API protette
     */
    @Bean
    @Profile("dev")
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabilita CSRF per chiamate API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SWAGGER_WHITELIST).permitAll() // Swagger accessibile
                        .requestMatchers("/api/auth/register").permitAll() // Permetti registrazione utente
                        .anyRequest().authenticated() // Tutto il resto richiede login
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No sessione server
                .httpBasic(Customizer.withDefaults()); // Basic auth (utile per test API/Swagger)

        return http.build();
    }

    /**
     * Configurazione Security per ambiente di produzione (qualsiasi profilo tranne dev)
     * Swagger protetto da login
     */
    @Bean
    @Profile("!dev")
    public SecurityFilterChain prodSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Se usi solo API REST, meglio tenerlo disabilitato anche in prod
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SWAGGER_WHITELIST).authenticated() // Swagger protetto da login
                        .requestMatchers("/api/auth/register").permitAll() // Permetti registrazione
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Se in futuro passi a JWT
                .httpBasic(Customizer.withDefaults()); // Basic auth (puoi aggiungere formLogin se preferisci)

        return http.build();
    }

    /**
     * Encoder per password con BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
