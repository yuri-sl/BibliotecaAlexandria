package com.bookshop.alexandriabook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desabilita a proteção contra CSRF. Não exige o Token extra contra CSRF
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/admin/**").permitAll().hasRole("ADMIN")
//                        .requestMatchers("/user/**").permitAll().hasAnyRole("USER","ADMIN")
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
        UserDetails user1 = User.builder()
                .username("kira")
                .password(passwordEncoder().encode("k@123"))
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("Goku")
                .password(passwordEncoder().encode("g@123"))
                .roles("USER")
                .build();

        UserDetails user3 = User
                .withDefaultPasswordEncoder()
                .username("harsh")
                .password(passwordEncoder().encode("h@123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,user2,user3);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return  provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);

        return source;

    }
}
