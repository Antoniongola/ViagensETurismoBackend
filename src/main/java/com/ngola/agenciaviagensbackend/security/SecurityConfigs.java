package com.ngola.agenciaviagensbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfigs {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authHttp -> authHttp.requestMatchers(HttpMethod.GET, "/api/login").permitAll())
                .authorizeHttpRequests(authHttp -> authHttp.requestMatchers(HttpMethod.POST, "/api/login").permitAll())
                .authorizeHttpRequests(authHttp -> authHttp.requestMatchers(HttpMethod.POST, "/api/cadastro").permitAll())
                .authorizeHttpRequests(authHttp-> authHttp.requestMatchers(HttpMethod.GET, "/api/cadastro").permitAll())
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .build();
    }
}
