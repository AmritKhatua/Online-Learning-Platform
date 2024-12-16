package com.hyscaler.Online_Learning_Platform.service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyscaler.Online_Learning_Platform.entity.Course;
import com.hyscaler.Online_Learning_Platform.entity.Progress;
import com.hyscaler.Online_Learning_Platform.entity.User;
import com.hyscaler.Online_Learning_Platform.exception.ResourceNotFound;
import com.hyscaler.Online_Learning_Platform.repository.CourseRepo;
import com.hyscaler.Online_Learning_Platform.repository.LessonRepo;
import com.hyscaler.Online_Learning_Platform.repository.ProgressRepo;
import com.hyscaler.Online_Learning_Platform.repository.QuizRepo;
import com.hyscaler.Online_Learning_Platform.repository.UserRepo;

@Service
public class ProgressService {
    
    @Autowired
    private ProgressRepo progressRepo;

    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseRepo courseRepo;

    //  public Progress getOrCreateProgress(Long userId, Long courseId) {
    //     return progressRepo.findByUserIdAndCourseId(userId, courseId)
    //         .orElseGet(() -> {
    //             Progress progress = new Progress();
    //             progress.setUser(new User(userId)); // Simplified; fetch user for production
    //             progress.setCourse(new Course(courseId)); // Simplified; fetch course for production
    //             return progressRepo.save(progress);
    //         });
    // }
    public Progress getOrCreateProgress(Long userId , Long courseId){
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user not found"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFound("Course Not found"));
        Progress progress = progressRepo.findByUserIdAndCourseId(userId, courseId).orElseThrow(() -> new ResourceNotFound());
        progress.setUser(user);
        progress.setCourse(course);
        Progress savedProgress = progressRepo.save(progress);
        return savedProgress;
    }

    public void trackQuiz(Long userId, Long courseId, Long quizId, boolean passed) {
        Progress progress = getOrCreateProgress(userId, courseId);
        if (!progress.getCompletedQuizIds().contains(quizId)) {
            progress.getCompletedQuizIds().add(quizId);
            progress.setQuizzesAttempted(progress.getQuizzesAttempted() + 1);
            if (passed) {
                progress.setQuizzesPassed(progress.getQuizzesPassed() + 1);
            }
            progressRepo.save(progress);
        }
    }


    public List<Progress> getProgressForStudent(Long studentId) {
        return progressRepo.findByUser_Id(studentId);
    }

    public void trackAssignmentSubmission(Long userId, Long courseId, boolean submitted, double grade) {
        Progress progress = progressRepo.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceNotFound("Progress not found!"));
        progress.setAssignmentSubmitted(submitted);
        progress.setAssignmentGrade(grade);
        progressRepo.save(progress);
    }

    // private void validateEnrollment(Long userId, Long courseId) {
    //     Course course = courseRepo.findById(courseId)
    //             .orElseThrow(() -> new ResourceNotFound("Course not found!"));
    //     boolean isEnrolled = course.getStudents().stream().anyMatch(student -> student.getId().equals(userId));
    //     if (!isEnrolled) {
    //         throw new AccessDeniedException("User is not enrolled in this course!");
    //     }
    // }

    // public void markLessonCompleted(Long userId, Long courseId, Long lessonId) {
    //     validateEnrollment(userId, courseId);
    //     Progress progress = progressRepo.findByUserIdAndCourseId(userId, courseId)
    //             .orElseThrow(() -> new ResourceNotFound("Progress not found!"));
    //     if (!progress.getCompletedLessonIds().contains(lessonId)) {
    //         progress.getCompletedLessonIds().add(lessonId);
    //         progressRepo.save(progress);
    //     }
    // }
}
