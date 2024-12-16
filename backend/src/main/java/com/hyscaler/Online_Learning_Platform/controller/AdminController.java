package com.hyscaler.Online_Learning_Platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyscaler.Online_Learning_Platform.entity.User;
import com.hyscaler.Online_Learning_Platform.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return new ResponseEntity<List<User>>(allUser,HttpStatus.OK);
    }
}
