package com.hyscaler.Online_Learning_Platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hyscaler.Online_Learning_Platform.entity.User;
import com.hyscaler.Online_Learning_Platform.repository.UserRepo;

    @Service
    public class CustomUserDetailsService implements UserDetailsService{

        @Autowired
        private UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            // Fetch the user from the database
            User user = userRepo.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }

            // Return a Spring Security User with roles (customize this as needed)
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())  // Make sure the password is hashed in the database
                    .roles(user.getRole().name())  // Convert Enum to String
                    .build();
        }
        
    }
