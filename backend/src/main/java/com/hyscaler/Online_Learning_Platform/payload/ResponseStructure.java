package com.hyscaler.Online_Learning_Platform.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseStructure <T> {
    private String message;
	private int statusCode;
    private T data;
}
    