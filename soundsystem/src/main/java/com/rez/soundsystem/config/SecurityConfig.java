package com.rez.soundsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // <-- TAMBAHKAN ANOTASI INI
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ✅ Nonaktifkan CSRF karena kita pakai JWT (stateless)
                .csrf(csrf -> csrf.disable())

                // ✅ Aktifkan CORS
                .cors(cors -> {
                })

                // ✅ Atur endpoint mana yang bebas dan mana yang harus autentikasi
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**", // login/register
                                "/v3/api-docs/**", // Swagger docs
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**")
                        .permitAll() // boleh diakses tanpa token
                        .anyRequest().authenticated() // lainnya harus JWT
                )

                // ✅ Tidak menggunakan session (karena pakai token JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // ✅ Tambahkan filter JWT sebelum UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ Tambahkan konfigurasi CORS di sini (langsung dalam SecurityConfig)
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // izinkan cookie/token
        config.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:3000", // Vue (Vite)
                "http://localhost:5173" // jika pakai port ini
        ));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setExposedHeaders(Arrays.asList("Authorization")); // biar FE bisa baca JWT header

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
