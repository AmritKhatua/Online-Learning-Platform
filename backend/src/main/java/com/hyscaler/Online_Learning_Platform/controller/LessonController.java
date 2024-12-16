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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hyscaler.Online_Learning_Platform.entity.Lesson;
import com.hyscaler.Online_Learning_Platform.service.LessonService;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;
    

    // Create a new lesson
    @PostMapping("/create")
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.saveLesson(lesson));
    }

    // Get a lesson by ID
    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    // Get all lessons for a specific course
    // @GetMapping("/course/{courseId}")
    // public ResponseEntity<List<Lesson>> getLessonsByCourseId(@PathVariable Long courseId) {
    //     return ResponseEntity.ok(lessonService.getLessonsByCourseId(courseId));
    // }

    // Delete a lesson by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }

     @PostMapping("/{id}/upload-material")
    public ResponseEntity<Lesson> uploadMaterial(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Lesson updatedLesson = lessonService.uploadMaterial(id, file);
        return new ResponseEntity<>(updatedLesson, HttpStatus.OK);
    }
}
