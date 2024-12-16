package com.hyscaler.Online_Learning_Platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyscaler.Online_Learning_Platform.entity.Assignment;

public interface AssignmentRepo extends JpaRepository<Assignment, Long> {
    // Add custom queries if needed, e.g., find assignments by course ID
    List<Assignment> findByCourseId(Long courseId);
    
}
