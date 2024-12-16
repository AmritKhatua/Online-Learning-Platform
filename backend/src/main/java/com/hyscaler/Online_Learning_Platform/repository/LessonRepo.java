package com.hyscaler.Online_Learning_Platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyscaler.Online_Learning_Platform.entity.Lesson;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
    // List<Lesson> findByCourseId(Long courseId);
    // List<Lesson> findByCourseId(Long courseId); // Fetch lessons for a specific course
    List<Lesson> findByCourseId(Long courseId); // Fetch lessons for a specific course
}
