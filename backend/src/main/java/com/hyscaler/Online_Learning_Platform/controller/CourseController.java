package com.hyscaler.Online_Learning_Platform.controller;

import java.nio.file.AccessDeniedException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyscaler.Online_Learning_Platform.entity.Course;
import com.hyscaler.Online_Learning_Platform.entity.User;
import com.hyscaler.Online_Learning_Platform.exception.ResourceNotFound;
import com.hyscaler.Online_Learning_Platform.repository.UserRepo;
import com.hyscaler.Online_Learning_Platform.service.CourseService;
import com.hyscaler.Online_Learning_Platform.service.UserService;

@RestController 
@RequestMapping("/course")
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

   @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestParam Long userId, @RequestBody Course course) {
    // Fetch the user (creator) from the database
    User creator = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found!"));
    course.setCreator(creator);
    Course createdCourse = courseService.saveCourse(course);
    return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
}

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return new ResponseEntity<Course>(course,HttpStatus.CREATED);
    }

    //-----------------------
    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Course> enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        Course updatedCourse = courseService.enrollStudentInCourse(courseId, studentId);
        return new ResponseEntity<Course>(updatedCourse,HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Course> removeStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        Course updatedCourse = courseService.removeStudentFromCourse(courseId, studentId);
        return ResponseEntity.ok(updatedCourse);
    }


    @GetMapping("/filter/category/{categoryId}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable Long categoryId) {
        List<Course> courses = courseService.getCoursesByCategory(categoryId);
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }

    @GetMapping("/filter/tag/{tag}")
    public ResponseEntity<List<Course>> getCoursesByTag(@PathVariable String tag) {
        List<Course> courses = courseService.getCoursesByTag(tag);
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<Course> publishCourse(@PathVariable Long id) {
        Course course = courseService.publishCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PutMapping("/{id}/unpublish")
    public ResponseEntity<Course> unpublishCourse(@PathVariable Long id) {
        Course course = courseService.unpublishCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/published")
    public ResponseEntity<List<Course>> getPublishedCourses() {
        List<Course> courses = courseService.getPublishedCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestParam Long userId, @RequestBody Course updatedCourse) throws AccessDeniedException {
        Course course = courseService.updateCourse(id, userId, updatedCourse);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id, @RequestParam Long userId) throws AccessDeniedException {
        courseService.deleteCourse(id, userId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

//     @GetMapping("/{userId}/enrolled-courses")
//     public ResponseEntity<List<Course>> getEnrolledCourses(@PathVariable Long userId) {
//     User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found!"));
//     return new ResponseEntity<>(user.getCoursesEnrolled(), HttpStatus.OK);
// }

    
@GetMapping("/{courseId}/enrolled-students")
public ResponseEntity<List<User>> getEnrolledStudents(@PathVariable Long courseId) {
    List<User> students = userService.getStudentsByCourseId(courseId);
    return new ResponseEntity<>(students, HttpStatus.OK);
}


}
