package com.rez.soundsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // ✅ Nonaktifkan CSRF karena pakai API stateless
            .csrf(csrf -> csrf.disable())
            
            // ✅ Aktifkan konfigurasi CORS dari CorsConfig
            .cors(cors -> {})

            // ✅ Atur izin endpoint
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/auth/**",          // endpoint login/register
                    "/v3/api-docs/**",       // swagger
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**"
                ).permitAll()               // boleh diakses tanpa token
                .anyRequest().authenticated() // sisanya harus pakai JWT
            )

            // ✅ Session stateless (JWT)
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        // ✅ Tambahkan JWT filter sebelum filter login default Spring
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
