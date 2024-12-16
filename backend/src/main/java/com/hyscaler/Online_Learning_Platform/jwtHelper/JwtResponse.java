package com.hyscaler.Online_Learning_Platform.jwtHelper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String role;
    private Long userId;
    private String userName;
    private String userEmail;
    }
    

