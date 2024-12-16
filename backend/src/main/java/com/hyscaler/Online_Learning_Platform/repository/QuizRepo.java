package com.hyscaler.Online_Learning_Platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyscaler.Online_Learning_Platform.entity.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Long>{
    // List<Quiz> findByCourseId(Long courseId);
    
     List<Quiz> findByCourseId(Long courseId); // Fetch quizzes for a specific course
}
