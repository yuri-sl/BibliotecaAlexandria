package com.bookshop.alexandriabook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    //Bean que permite a gente escolher como será a autenticação do usuario.
    // Ao implementar o UserDetailsService, o SpringSecurity para de usar as credenciais definidas no applicationproperties
    //Para autenticação
    @Bean
    public UserDetailsService userDetailsService(){
        //Criando usuário individuais para serem autenticados
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("kira")
                .password("k@123")
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("Goku")
                .password("g@123")
                .roles("USER")
                .build();

        UserDetails user3 = User
                .withDefaultPasswordEncoder()
                .username("harsh")
                .password("h@123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,user2,user3);
    }
}
