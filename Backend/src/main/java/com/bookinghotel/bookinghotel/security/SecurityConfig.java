package com.bookinghotel.bookinghotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bookinghotel.bookinghotel.filter.CustomJwtFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomJwtFilter customJwtFilter;
// Khoi tao chuan ma hoa su dung va luu tren IOC
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeHttpRequests(author -> {
                    author.requestMatchers("/author/**").permitAll();
                    author.requestMatchers("/bookings/**").hasRole("ADMIN");
                    author.requestMatchers("/user/**").permitAll();
                    author.requestMatchers("/file/**").hasRole("ADMIN");
                    author.requestMatchers("/room/**").permitAll();
                    author.requestMatchers("/role/**").hasRole("ADMIN");
                })
                .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
