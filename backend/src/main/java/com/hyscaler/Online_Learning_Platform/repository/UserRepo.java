package com.hyscaler.Online_Learning_Platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyscaler.Online_Learning_Platform.entity.Role;
import com.hyscaler.Online_Learning_Platform.entity.User;

public interface UserRepo extends JpaRepository<User,Long> {
    
    List<User> findByRole(Role role); // Fetch users by their role
    User findByEmail(String email);  // Fetch a user by email
    List<User> findByCoursesEnrolled_Id(Long courseId); // Fetch students by course ID
    
}
