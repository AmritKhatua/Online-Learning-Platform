package com.hyscaler.Online_Learning_Platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hyscaler.Online_Learning_Platform.payload.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
    ResponseStructure<String> structure =  new ResponseStructure<String>();
    
    @ExceptionHandler(EmailAlreadyExisted.class)
    public ResponseEntity<ResponseStructure<String>> emailAlreadyExist(EmailAlreadyExisted emailAlreadyExist){
       
        structure.setMessage("cannot register same user again .....");
        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
        structure.setData(emailAlreadyExist.getMessage());
        return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFound userNotFound){
        structure.setMessage("User Not FOund");
        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
        structure.setData(userNotFound.getMessage());
        return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ResponseStructure<String>> resourceNotFound(ResourceNotFound resourceNotFound){
        structure.setMessage("Resource Not FOund");
        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
        structure.setData(resourceNotFound.getMessage());
        return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
    }
}
