package com.hyscaler.Online_Learning_Platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyscaler.Online_Learning_Platform.entity.Quiz;
import com.hyscaler.Online_Learning_Platform.repository.QuizRepo;

@Service
public class QuizService {
    
     @Autowired
    private QuizRepo quizRepo;

    public Quiz createQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }

    public Quiz getQuizById(Long id) {
        return quizRepo.findById(id).orElse(null);
    }

    public Quiz updateQuiz(Long id, Quiz updatedQuiz) {
        Quiz quiz = quizRepo.findById(id).orElse(null);
        if (quiz != null) {
            quiz.setTitle(updatedQuiz.getTitle());
            quiz.setQuestions(updatedQuiz.getQuestions());
            quiz.setCourse(updatedQuiz.getCourse());
            return quizRepo.save(quiz);
        }
        return null;
    }

    public void deleteQuiz(Long id) {
        quizRepo.deleteById(id);
    }

}
