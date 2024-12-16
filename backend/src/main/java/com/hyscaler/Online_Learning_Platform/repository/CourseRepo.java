package com.hyscaler.Online_Learning_Platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyscaler.Online_Learning_Platform.entity.Course;

public interface CourseRepo  extends JpaRepository<Course , Long>{

    //List<Course> findByInstructorId(Long instructorId); // Fetch courses by instructor ID

    Course findByTitle(String title);

    List<Course> findByCategories_Id(Long categoryId);
    
    // Alternatively, if you still want to use tags, you can use:
    List<Course> findByTagsContaining(String tag);
    
    List<Course> findByPublished(boolean published);

    List<Course> findByCreator_Id(Long creatorId);
}
