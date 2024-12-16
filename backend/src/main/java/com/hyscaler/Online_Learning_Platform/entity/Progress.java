package com.hyscaler.Online_Learning_Platform.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ElementCollection
    private List<Long> completedLessonIds = new ArrayList<>(); // IDs of completed lessons

    @ElementCollection
    private List<Long> completedQuizIds = new ArrayList<>(); // IDs of completed quizzes

    private int quizzesAttempted = 0;
    private int quizzesPassed = 0;

    private boolean assignmentSubmitted = false;
    private double overallScore = 0.0;
    private double assignmentGrade; // Grade for the assignment
  

}
