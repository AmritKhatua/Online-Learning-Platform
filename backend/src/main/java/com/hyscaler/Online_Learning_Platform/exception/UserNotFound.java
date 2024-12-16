package com.hyscaler.Online_Learning_Platform.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserNotFound  extends RuntimeException{
    
    String message = "User not found !!!!";
	
	public String getMessage(String s) {
		return s;
	}
}
