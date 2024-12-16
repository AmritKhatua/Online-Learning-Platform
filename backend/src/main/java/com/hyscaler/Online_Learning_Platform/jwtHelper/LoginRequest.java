package com.hyscaler.Online_Learning_Platform.jwtHelper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    private String email;
    private String password;
}
