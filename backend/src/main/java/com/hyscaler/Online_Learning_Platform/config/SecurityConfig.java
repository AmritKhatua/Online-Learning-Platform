package com.hyscaler.Online_Learning_Platform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hyscaler.Online_Learning_Platform.filter.Jwtfilter;
import com.hyscaler.Online_Learning_Platform.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired
    private Jwtfilter jwtFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;  // Autowire your custom user details service

    // Define the SecurityFilterChain bean for HTTP security configurations   "user/fetchAll"
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/login", "/user/create","/user/all","/user/delete/**","/user/**").permitAll()  // Allow unauthenticated access
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Restrict to only users with ADMIN role
                // .requestMatchers("/travel/**").hasRole("TRAVEL_AGENT")  // Restrict to only users with TRAVEL_AGENT role
                .anyRequest().authenticated()  // All other requests must be authenticated
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);  // Add JWT filter before authentication filter

        return http.build();  // Builds the security filter chain
    }

    // Define the PasswordEncoder bean for hashing passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define the AuthenticationManager bean directly
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(customUserDetailsService)  // Use your custom UserDetailsService
                   .passwordEncoder(passwordEncoder())
                   .and()
                   .build();
    }
    
}
