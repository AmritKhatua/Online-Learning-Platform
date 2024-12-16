package com.hyscaler.Online_Learning_Platform.service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyscaler.Online_Learning_Platform.entity.Category;
import com.hyscaler.Online_Learning_Platform.entity.Course;
import com.hyscaler.Online_Learning_Platform.entity.User;
import com.hyscaler.Online_Learning_Platform.exception.ResourceNotFound;
import com.hyscaler.Online_Learning_Platform.repository.CategoryRepo;
import com.hyscaler.Online_Learning_Platform.repository.CourseRepo;
import com.hyscaler.Online_Learning_Platform.repository.UserRepo;


@Service
public class CourseService {
    
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public Course saveCourse(Course course) {
    // Fetch and attach categories
    List<Category> attachedCategories = new ArrayList<>();
    for (Category category : course.getCategories()) {
        Category attachedCategory = categoryRepo.findById(category.getId())
            .orElseThrow(() -> new ResourceNotFound("Category not found for ID: " + category.getId()));
        attachedCategories.add(attachedCategory);
    }
    course.setCategories(attachedCategories);

    // Save the course
    return courseRepo.save(course);
}
    // Get a course by ID
    public Course getCourseById(Long id) {
        return courseRepo.findById(id).orElseThrow(() -> new ResourceNotFound());
    }

    //  // Get courses taught by a specific instructor
    //  public List<Course> getCoursesByInstructorId(Long instructorId) {
    //     return courseRepo.findByInstructorId(instructorId);
    // }

     // Enroll a student in a course
     public Course enrollStudentInCourse(Long courseId, Long studentId) {
        Course course = courseRepo.findById(courseId).orElse(null);
        User student = userRepo.findById(studentId).orElse(null);
        if (course != null && student != null) {
            // course.getStudents().add(student);
            return courseRepo.save(course);
        }
        return null;
    }

    // Remove a student from a course
    public Course removeStudentFromCourse(Long courseId, Long studentId) {
        Course course = courseRepo.findById(courseId).orElse(null);
        User student = userRepo.findById(studentId).orElse(null);
        if (course != null && student != null) {
            // course.getStudents().remove(student);
            return courseRepo.save(course);
        }
        return null;
    }

    // Delete a course by ID
    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }

    public List<Course> getCoursesByCategory(Long categoryId) {
        List<Course> categories = courseRepo.findByCategories_Id(categoryId);
        return categories;
    }

    public List<Course> getCoursesByTag(String tag) {
         List<Course> tags = courseRepo.findByTagsContaining(tag);
         return tags;
    }

    public Course publishCourse(Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFound("Course not found!"));
        course.setPublished(true);
        return courseRepo.save(course);
    }

    public Course unpublishCourse(Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFound("Course not found!"));
        course.setPublished(false);
        return courseRepo.save(course);
    }

    public List<Course> getPublishedCourses() {
        return courseRepo.findByPublished(true);
    }

    public Course updateCourse(Long courseId, Long userId, Course updatedCourse) throws AccessDeniedException {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFound("Course not found!"));
        if (!course.getCreator().getId().equals(userId)) {
            throw new AccessDeniedException("You are not allowed to update this course!");
        }
        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        course.setTags(updatedCourse.getTags());
        course.setCategories(updatedCourse.getCategories());
        return courseRepo.save(course);
    }

    public void deleteCourse(Long courseId, Long userId) throws AccessDeniedException {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFound("Course not found!"));
        if (!course.getCreator().getId().equals(userId)) {
            throw new AccessDeniedException("You are not allowed to delete this course!");
        }
        courseRepo.deleteById(courseId);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }
}
