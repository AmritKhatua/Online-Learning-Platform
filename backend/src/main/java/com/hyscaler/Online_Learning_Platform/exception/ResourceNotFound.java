package com.hyscaler.Online_Learning_Platform.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResourceNotFound extends RuntimeException{
    
    String message = "Resource Not Found !!!!";
	
	public String getMessage(String s) {
		return s;
	}
}
