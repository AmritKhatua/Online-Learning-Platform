package com.hyscaler.Online_Learning_Platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyscaler.Online_Learning_Platform.entity.Role;
import com.hyscaler.Online_Learning_Platform.entity.User;
import com.hyscaler.Online_Learning_Platform.jwtHelper.JwtResponse;

import com.hyscaler.Online_Learning_Platform.jwtHelper.LoginRequest;
import com.hyscaler.Online_Learning_Platform.payload.ResponseStructure;
import com.hyscaler.Online_Learning_Platform.repository.UserRepo;
import com.hyscaler.Online_Learning_Platform.service.UserService;
import com.hyscaler.Online_Learning_Platform.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User registerUser = userService.registerUser(user);
        return new ResponseEntity<User>(registerUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<User>(user,HttpStatus.FOUND);
    }
      @GetMapping("/userbyroles")
    public ResponseEntity<List<User>> getUsersByRole(@RequestParam Role role) {
        List<User> users = userService.getUsersByRole(role);
        return new  ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        // return ResponseEntity.noContent().build();
        return new ResponseEntity<ResponseStructure>(HttpStatus.OK);
    }

    // @PreAuthorize("ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return new ResponseEntity<List<User>>(allUser,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail());
    
        if (user != null && loginRequest.getPassword().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
            return ResponseEntity.ok(new JwtResponse(
                token,
                user.getRole().name(),
                user.getId(),
                user.getName(),
                user.getEmail()
            ));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }  
}


