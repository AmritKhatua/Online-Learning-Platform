package com.hyscaler.Online_Learning_Platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyscaler.Online_Learning_Platform.entity.Quiz;
import com.hyscaler.Online_Learning_Platform.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
public class QuizController {
    
    @Autowired
    private QuizService quizService;

     @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz createdQuiz = quizService.createQuiz(quiz);
        return new ResponseEntity<Quiz>(createdQuiz, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<List<Quiz>>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz != null) {
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {
        Quiz quiz = quizService.updateQuiz(id, updatedQuiz);
        if (quiz != null) {
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
