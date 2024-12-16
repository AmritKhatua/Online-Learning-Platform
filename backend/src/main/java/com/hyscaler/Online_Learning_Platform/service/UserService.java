package com.hyscaler.Online_Learning_Platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyscaler.Online_Learning_Platform.entity.Role;
import com.hyscaler.Online_Learning_Platform.entity.User;
import com.hyscaler.Online_Learning_Platform.exception.EmailAlreadyExisted;
import com.hyscaler.Online_Learning_Platform.exception.ResourceNotFound;
import com.hyscaler.Online_Learning_Platform.exception.UserNotFound;
import com.hyscaler.Online_Learning_Platform.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public User registerUser(User user){
        User existUser = userRepo.findByEmail(user.getEmail());
        if(existUser !=null){
            throw new EmailAlreadyExisted();
        }
        User savedUser = userRepo.save(user);
        return savedUser;
    }

   // Get user by ID
     public User getUserById(Long id) {
        User existUser = userRepo.findById(id).orElseThrow(() -> new UserNotFound());
        return existUser;
    }
    
    // Get users by role
    public List<User> getUsersByRole(Role role) {
        return userRepo.findByRole(role);           // here handel the exception.....
    }

    // Get all students enrolled in a specific course
    public List<User> getStudentsByCourseId(Long courseId) {
        return userRepo.findByCoursesEnrolled_Id(courseId);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

     // get All user
     public List<User> getAllUser(){
        List<User> users = userRepo.findAll();
        return users;
    }

}
