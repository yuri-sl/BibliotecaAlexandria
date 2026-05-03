package com.bookshop.alexandriabook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desabilita a proteção contra CSRF. Não exige o Token extra contra CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())// Usuário autenticado consegue realizar qualquer requisição
                .httpBasic(Customizer.withDefaults())//Para funcionar com o PostMan
                .formLogin(Customizer.withDefaults()) // Formulário de login com as credenciais padrões
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//Cada requisição deve provar a sua autenticação
                .build();
    }
}
