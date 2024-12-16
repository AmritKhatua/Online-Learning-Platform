package com.hyscaler.Online_Learning_Platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyscaler.Online_Learning_Platform.entity.Progress;
import com.hyscaler.Online_Learning_Platform.service.ProgressService;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
@RequestMapping("/progress")
public class ProgressController {
    
    @Autowired
    private ProgressService progressService;
    
//    @PutMapping("/lesson/{userId}/{courseId}/{lessonId}")
// public ResponseEntity<Void> markLessonCompleted(
//         @PathVariable Long userId, 
//         @PathVariable Long courseId, 
//         @PathVariable Long lessonId) {
//     progressService.markLessonCompleted(userId, courseId, lessonId);
//     return ResponseEntity.ok().build();
// }

@PutMapping("/quiz/{userId}/{courseId}/{quizId}/{passed}")
public ResponseEntity<Void> trackQuiz(
        @PathVariable Long userId, 
        @PathVariable Long courseId, 
        @PathVariable Long quizId, 
        @PathVariable boolean passed) {
    progressService.trackQuiz(userId, courseId, quizId, passed);
    return ResponseEntity.ok().build();
}

    @GetMapping("/")
    public ResponseEntity<Progress> getProgress(@RequestParam Long userId, @RequestParam Long courseId) {
        return ResponseEntity.ok(progressService.getOrCreateProgress(userId, courseId));
    }

    @GetMapping("/student/{userId}")
    public ResponseEntity<List<Progress>> getProgressForStudent(@PathVariable Long userId) {
        List<Progress> progressList = progressService.getProgressForStudent(userId);
        return new ResponseEntity<>(progressList, HttpStatus.OK);
    }

    @PutMapping("/assignment")
public ResponseEntity<Void> trackAssignmentSubmission(@RequestParam Long userId, @RequestParam Long courseId,
                                                      @RequestParam boolean submitted, @RequestParam double grade) {
    progressService.trackAssignmentSubmission(userId, courseId, submitted, grade);
    return ResponseEntity.ok().build();
}
}
